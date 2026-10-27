package com.razorpay.payment.gateway.adapters;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.razorpay.common.enums.PaymentMethod;
import com.razorpay.payment.gateway.PaymentAdapter;
import com.razorpay.payment.gateway.dto.PaymentRequest;
import com.razorpay.payment.gateway.dto.PaymentResult;
import com.razorpay.payment.processor.PaymentProcessorRouter;
import com.razorpay.payment.processor.dto.PaymentProcessorRequest;
import com.razorpay.payment.processor.dto.PaymentProcessorResponse;
import com.razorpay.payment.processor.strategy.NetBankingPaymentProcessor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class NetBankingAdapter implements PaymentAdapter{
	
	private final PaymentProcessorRouter paymentProcessorRouter;
	
	@Override
	public PaymentResult initiate(PaymentRequest request) {
		log.info("Initiate Payment with NetBankingAdapter, paymentId: {}", request.paymentId());
		try {
            PaymentProcessorRequest paymentProcessorRequest = PaymentProcessorRequest.nonCard(
                    request.paymentId(),
                    PaymentMethod.NETBANKING,
                    request.amount(),
                    request.methodDetails()
            );

            PaymentProcessorResponse paymentProcessorResponse =
                    paymentProcessorRouter.charge(paymentProcessorRequest);

            return switch (paymentProcessorResponse) {
                case PaymentProcessorResponse.Failure failure ->
                        new PaymentResult.Failure(failure.errorCode(), failure.errorDescription());

                case PaymentProcessorResponse.Pending pending ->
                        new PaymentResult.Pending(pending.processorReference());

                case PaymentProcessorResponse.Success success -> new PaymentResult.Success(success.bankReference());

            };
        } catch(Exception e) {
            log.warn("NetBanking failed, paymentId: {}", request.paymentId());
            return new PaymentResult.Failure("NBK_FAILED", e.getMessage());
        }
    
	
	}

	@Override
	public PaymentResult capture(UUID paymentId) {
		
		return new PaymentResult.Success("NBK_REF");
	}
}




















