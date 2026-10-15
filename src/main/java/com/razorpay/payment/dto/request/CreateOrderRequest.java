package com.razorpay.payment.dto.request;

import java.time.LocalDateTime;
import java.util.Map;

import com.razorpay.common.entity.Money;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateOrderRequest(
		
		@NotNull(message = "Amount is required")
		Money amount,
		
		@Size(max = 100)
		String receipt,      // order_id (known to merchant)
		
		Map<String, Object> notes,
		
		LocalDateTime expireAt
		
		) {

}
