package com.razorpay.payment.gateway;

import java.util.UUID;

import com.razorpay.payment.gateway.dto.PaymentRequest;
import com.razorpay.payment.gateway.dto.PaymentResult;

public interface PaymentAdapter {
	
	PaymentResult initiate(PaymentRequest request);

	PaymentResult capture(UUID paymentId);

}
