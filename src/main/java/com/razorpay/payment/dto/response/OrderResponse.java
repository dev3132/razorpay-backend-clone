package com.razorpay.payment.dto.response;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import com.razorpay.common.entity.Money;
import com.razorpay.common.enums.OrderStatus;

public record OrderResponse(
		UUID id, 
		UUID merchantId, 
//		UUID customerId, 
		String receipt, 
		Money amount, 
		OrderStatus status,
		Integer attempts, 
		Map<String, Object> notes, 
		LocalDateTime expiresAt, 
		LocalDateTime createdAt

) {
}
