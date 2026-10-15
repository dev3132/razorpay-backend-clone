package com.razorpay.payment.dto.response;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.razorpay.common.entity.Money;
import com.razorpay.common.enums.PaymentMethod;
import com.razorpay.common.enums.PaymentStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentResponse(
		UUID id,
		UUID orderId,
		UUID merchantId,
		Money amount,
		PaymentStatus status,
		PaymentMethod method,
		Map<String, Object> methodDetails,
		String errorCode,
		String errorDescription,
		LocalDateTime capturedAt, 
		LocalDateTime createdAt 
		
		) {

}







