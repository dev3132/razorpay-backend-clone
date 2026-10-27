package com.razorpay.payment.service.impl;

import com.razorpay.payment.mapper.PaymentMapper;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.razorpay.common.enums.OrderStatus;
import com.razorpay.common.enums.PaymentEvent;
import com.razorpay.common.enums.PaymentStatus;
import com.razorpay.common.exception.BusinessRuleViolationException;
import com.razorpay.common.exception.ResourceNotFoundException;
import com.razorpay.payment.dto.request.PaymentInitRequest;
import com.razorpay.payment.dto.response.PaymentResponse;
import com.razorpay.payment.entity.OrderRecord;
import com.razorpay.payment.entity.Payment;
import com.razorpay.payment.gateway.PaymentGatewayRouter;
import com.razorpay.payment.gateway.dto.PaymentRequest;
import com.razorpay.payment.gateway.dto.PaymentResult;
import com.razorpay.payment.repository.OrderRepository;
import com.razorpay.payment.repository.PaymentRepository;
import com.razorpay.payment.service.PaymentService;
import com.razorpay.payment.statemachine.PaymentTransitionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

	private final OrderRepository orderRepository;
	private final PaymentRepository paymentRepository;
	private final PaymentGatewayRouter paymentGatewayRouter;
	private final PaymentMapper paymentMapper;
	private final PaymentTransitionService paymentTransitionService;

	@Override
	@Transactional
	public PaymentResponse initiate(UUID merchantId, PaymentInitRequest request) {

		OrderRecord order = orderRepository.findByIdAndMerchantId(request.orderId(), merchantId)
				.orElseThrow(() -> new ResourceNotFoundException("order", request.orderId()));

		if (order.getOrderStatus() != OrderStatus.CREATED && order.getOrderStatus() != OrderStatus.ATTEMPTED) {
			throw new BusinessRuleViolationException("ORDER_NOT_PAYBLE",
					"order cannot accept payment in status: " + order.getOrderStatus());
		}
		order.setOrderStatus(OrderStatus.ATTEMPTED);
		order.setAttempts(order.getAttempts() + 1);

		Payment payment = Payment.builder().order(order).merchantId(merchantId).amount(order.getAmount())
				.paymentStatus(PaymentStatus.CREATED).paymentMethod(request.method())
				.methodDetails(request.methodDetails()).build();

		payment = paymentRepository.save(payment);

		PaymentRequest paymentRequest = new PaymentRequest(payment.getId(), order.getId(), merchantId,
				order.getAmount(), request.method(), request.methodDetails());
		
		paymentTransitionService.apply(payment, PaymentEvent.AUTHORIZE_ATTEMPT);
		PaymentResult result = paymentGatewayRouter.initiate(paymentRequest);
		 switch (result) {
         case PaymentResult.Pending pending -> payment.setProcessorReference(pending.registrationRef());
         case PaymentResult.Failure failure -> {
//             payment.setPaymentStatus(PaymentStatus.FAILED);
        	 paymentTransitionService.apply(payment, PaymentEvent.AUTHORIZE_FAIL);
             payment.setErrorCode(failure.errorCode());
             payment.setErrorDescription(failure.errorDescription());
         }
         case PaymentResult.Success success -> {
             
         }
     }


		payment = paymentRepository.save(payment);
		orderRepository.save(order);

		 //TODO: send outbox kafka event
		
		return paymentMapper.toResponse(payment);
		
		
	}

	@Override
	public PaymentResponse capture(UUID merchantId, UUID paymentId) {

		Payment payment = paymentRepository.findByIdAndMerchantId(paymentId, merchantId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment", paymentId));

	//	payment.setPaymentStatus(PaymentStatus.CAPTURING); // TODO state-machine

		paymentTransitionService.apply(payment, PaymentEvent.CAPTURE_REQUEST);
		
		PaymentResult paymentResult = paymentGatewayRouter.capture(payment.getPaymentMethod(), paymentId);

		if (paymentResult instanceof PaymentResult.Success success) {
//			payment.setPaymentStatus(PaymentStatus.CAPTURED);
			
			paymentTransitionService.apply(payment, PaymentEvent.CAPTURE_SUCCESS);
			payment.setCapturedAt(LocalDateTime.now());
			log.info("Payment captured, paymentID: {}", paymentId);
		} else if (paymentResult instanceof PaymentResult.Failure failure) {
//			payment.setPaymentStatus(PaymentStatus.AUTHORIZED);
			paymentTransitionService.apply(payment, PaymentEvent.CAPTURE_FAIL);

			
			payment.setErrorCode(failure.errorCode());
			payment.setErrorDescription(failure.errorDescription());
			log.warn("Payment capture failed, paymentID: {}", paymentId);
		}
		
		payment = paymentRepository.save(payment);
		return paymentMapper.toResponse(payment);
	}

	@Override
	@Transactional
	public void resolveAuthorization(UUID paymentId, boolean approve, String bankRef, String errorCode,
			String errorDescription) {
		
		Payment payment = paymentRepository.findById(paymentId)
              .orElseThrow(() -> new ResourceNotFoundException("Payment", paymentId));
		
		if (payment.getPaymentStatus() != PaymentStatus.AUTHORIZING) {
            log.warn("Payment is not in Authorizing state, paymentID: {}, status: {}", 
            		paymentId, payment.getPaymentStatus());
            return;
        }
		
		OrderRecord orderRecord = payment.getOrder();
		
		if (approve) {
			paymentTransitionService.apply(payment, PaymentEvent.AUTHORIZE_SUCCESS);
			payment.setBankReference(bankRef);
			payment.setAuthorizedAt(LocalDateTime.now());

			// Auto-capture
			paymentTransitionService.apply(payment, PaymentEvent.CAPTURE_REQUEST);
			PaymentResult captureResult = paymentGatewayRouter.capture(payment.getPaymentMethod(), paymentId);

			if (captureResult instanceof PaymentResult.Success success) {
				paymentTransitionService.apply(payment, PaymentEvent.CAPTURE_SUCCESS);
				payment.setCapturedAt(LocalDateTime.now());
				orderRecord.setOrderStatus(OrderStatus.PAID);
			} else if (captureResult instanceof PaymentResult.Failure failure) {
				paymentTransitionService.apply(payment, PaymentEvent.CAPTURE_FAIL);
				payment.setErrorCode(failure.errorCode());
				payment.setErrorDescription(failure.errorDescription());
			}

		} else {
			paymentTransitionService.apply(payment, PaymentEvent.AUTHORIZE_FAIL);
            payment.setErrorCode(errorCode);
            payment.setErrorDescription(errorDescription);
		}
		
		paymentRepository.save(payment);
        orderRepository.save(orderRecord);
        
        //TODO: send outbox kafka event
	}

}


























