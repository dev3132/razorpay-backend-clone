package com.razorpay.payment.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.razorpay.common.enums.OrderStatus;
import com.razorpay.common.exception.BusinessRuleViolationException;
import com.razorpay.common.exception.DuplicateResourceException;
import com.razorpay.common.exception.ResourceNotFoundException;
import com.razorpay.payment.dto.request.CreateOrderRequest;
import com.razorpay.payment.dto.response.OrderResponse;
import com.razorpay.payment.dto.response.PaymentResponse;
import com.razorpay.payment.entity.OrderRecord;
import com.razorpay.payment.entity.Payment;
import com.razorpay.payment.mapper.OrderMapper;
import com.razorpay.payment.mapper.PaymentMapper;
import com.razorpay.payment.repository.OrderRepository;
import com.razorpay.payment.repository.PaymentRepository;
import com.razorpay.payment.service.OrderService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
		
	private final OrderRepository orderRepository;
	private final PaymentRepository paymentRepository;
	private final PaymentMapper paymentMapper;
	private final OrderMapper orderMapper; 
	
	@Value("${payment.order.default-order-expiry-minutes:30}")
	private int defaultOrderExpiryMinutes;
	
	@Transactional
	public OrderResponse create(UUID merchantId, CreateOrderRequest request) {
		if(request.receipt() != null && orderRepository.existsByMerchantIdAndReceipt(merchantId, request.receipt())) {
			throw new DuplicateResourceException("ORDER_RECEIPT_DUPLICATE", 
					"Order with receipt already exists" + request.receipt());
		}
		
		
		OrderRecord order = OrderRecord.builder()
				.receipt(request.receipt())
				.amount(request.amount())
				.notes(request.notes())
				.merchantId(merchantId)
				.orderStatus(OrderStatus.CREATED)
				.expiresAt(request.expireAt() != null ? request.expireAt() :
					LocalDateTime.now().plusMinutes(defaultOrderExpiryMinutes))
				
				.build();
		
		
		//TODO   Kafka event publish about order creation
		
		order = orderRepository.save(order);
		return orderMapper.toResponse(order);
	}

	@Override
	public OrderResponse getById(UUID merchantId, UUID orderId) {
		OrderRecord order = orderRepository.findByIdAndMerchantId(merchantId, orderId)
				.orElseThrow(() -> new ResourceNotFoundException("order ", orderId));
		return orderMapper.toResponse(order);
	}

	@Override
	@Transactional
	public OrderResponse cancel(UUID merchantId, UUID orderId) {
		OrderRecord order = orderRepository.findByIdAndMerchantId(merchantId, orderId)
				.orElseThrow(() -> new ResourceNotFoundException("order ", orderId));
		
		if(order.getOrderStatus() == OrderStatus.CANCELLED || order.getOrderStatus() == OrderStatus.PAID) {
			throw new BusinessRuleViolationException("ORDER_CANNOT_CANCEL", 
					"cannot cancel order with status " + order.getOrderStatus().name());
		}
		order.setOrderStatus(OrderStatus.CANCELLED);
		order = orderRepository.save(order);
		
		return orderMapper.toResponse(order);
	}

	@Override
	public List<PaymentResponse> listPayments(UUID merchantId, UUID orderId) {
		OrderRecord order = orderRepository.findByIdAndMerchantId(merchantId, orderId)
				.orElseThrow(() -> new ResourceNotFoundException("order ", orderId));
		
		List<Payment> listPayment = paymentRepository.findByOrder_Id(order);
		
		return listPayment.stream()
				.map(payment -> paymentMapper.toResponse(payment))
				.collect(Collectors.toList());
				
	}
	
	
}




