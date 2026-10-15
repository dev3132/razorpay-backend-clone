package com.razorpay.payment.service;

import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;

import com.razorpay.payment.dto.request.CreateOrderRequest;
import com.razorpay.payment.dto.response.OrderResponse;
import com.razorpay.payment.dto.response.PaymentResponse;

import jakarta.validation.Valid;

public interface OrderService {

	OrderResponse create(UUID merchantId, CreateOrderRequest request);
	
	OrderResponse getById(UUID merchantId, UUID orderId);

	OrderResponse cancel(UUID merchantId, UUID orderId);
	
	List<PaymentResponse> listPayments(UUID merchantId, UUID orderId);
}
