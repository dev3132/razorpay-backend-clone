package com.razorpay.vault.service;

import java.util.Map;
import java.util.UUID;

import org.jspecify.annotations.Nullable;

import com.razorpay.common.entity.Money;
import com.razorpay.payment.processor.dto.PaymentProcessorResponse;
import com.razorpay.vault.dto.request.TokenizeRequest;
import com.razorpay.vault.dto.response.TokenizeResponse;

import jakarta.validation.Valid;

public interface VaultService {

	TokenizeResponse tokenize(TokenizeRequest request, UUID merchantId);
	
	PaymentProcessorResponse charge(UUID paymentId, String token, Money amount, Map<String, Object> methodDetails);
	
}
