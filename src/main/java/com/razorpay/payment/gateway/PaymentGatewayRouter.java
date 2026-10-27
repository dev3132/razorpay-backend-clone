package com.razorpay.payment.gateway;

import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.razorpay.common.enums.PaymentMethod;
import com.razorpay.payment.gateway.dto.PaymentRequest;
import com.razorpay.payment.gateway.dto.PaymentResult;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentGatewayRouter {
	
	private final Map<PaymentMethod, PaymentAdapter> paymentAdapaters;
	
	public PaymentResult initiate(PaymentRequest request) {
		PaymentAdapter adapter = paymentAdapaters.get(request.method());
		
		if(adapter == null) {
			throw new IllegalArgumentException("no payment adapter registere for method : " + request.method());
		}
		return adapter.initiate(request);
	}

	public PaymentResult capture(PaymentMethod paymentMethod, UUID paymentId) {
		PaymentAdapter adapter = paymentAdapaters.get(paymentMethod);

		if (adapter == null) {
			throw new IllegalArgumentException("no payment adapter registere for method : " + paymentMethod);
		}
		
		return adapter.capture(paymentId);
	}
}


















