package com.razorpay.merchant.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.razorpay.common.enums.Environment;

public record ApiKeyResponse(
		UUID id,
		String keyId,
		Environment environment,
		boolean isEnabled,
		LocalDateTime lastUsedAt,
		LocalDateTime createdAt
		
		) {

}
