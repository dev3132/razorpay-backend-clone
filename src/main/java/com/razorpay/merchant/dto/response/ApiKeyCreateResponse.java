package com.razorpay.merchant.dto.response;

import java.util.UUID;

import com.razorpay.common.enums.Environment;

public record ApiKeyCreateResponse(
		UUID id,
		String keyId,
		String keySecret,
		Environment environment
		
		) {

}
