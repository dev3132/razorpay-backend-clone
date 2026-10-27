package com.razorpay.payment.service;

import java.util.UUID;

import org.jspecify.annotations.Nullable;

import com.razorpay.payment.dto.request.PaymentInitRequest;
import com.razorpay.payment.dto.response.PaymentResponse;

public interface PaymentService {
	
	PaymentResponse initiate(UUID merchantId, PaymentInitRequest request);

	PaymentResponse capture(UUID merchantId, UUID paymentId);

	void resolveAuthorization(UUID paymentId , boolean approve, 
			String bankRef, String errorCode, String errorDescription);
}
