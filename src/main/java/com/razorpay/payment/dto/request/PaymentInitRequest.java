package com.razorpay.payment.dto.request;

import java.util.Map;
import java.util.UUID;

import com.razorpay.common.enums.PaymentMethod;

import jakarta.validation.constraints.NotNull;

public record PaymentInitRequest(
		
		@NotNull(message = "order Id is required")
		UUID orderId,
		
		@NotNull(message = "payment method is required")
		PaymentMethod method,
		
		Map<String, Object> methodDetails
	
		) {

}
