package com.razorpay.payment.processor;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.razorpay.common.enums.PaymentMethod;
import com.razorpay.payment.processor.dto.PaymentProcessorRequest;
import com.razorpay.payment.processor.dto.PaymentProcessorResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentProcessorRouter {
	
	private final Map<PaymentMethod, PaymentProcessor> paymentProcessors;
	

    public PaymentProcessorResponse charge(PaymentProcessorRequest request) {
        PaymentProcessor processor = paymentProcessors.get(request.method());
        if (processor == null) {
            throw new IllegalArgumentException("No payment processor registered for method: "+request.method());
        }
        return processor.charge(request);
        
    }    

}
