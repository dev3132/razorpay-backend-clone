package com.razorpay.payment.statemachine;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.razorpay.common.enums.PaymentActor;
import com.razorpay.common.enums.PaymentEvent;
import com.razorpay.common.enums.PaymentStatus;
import com.razorpay.payment.entity.Payment;
import com.razorpay.payment.entity.PaymentTransitionLog;
import com.razorpay.payment.repository.PaymentTransitionLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentTransitionService {

	private final PaymentTransitionLogRepository paymentTransitionLogRepository;
	private final PaymentStateMachine paymentStateMachine;
	
	 public PaymentStatus apply(Payment payment, PaymentEvent event) {
	        PaymentStatus next = paymentStateMachine.transition(payment.getPaymentStatus(), event);
	        PaymentTransitionLog log = PaymentTransitionLog.builder()
	                .payment(payment)
	                .fromStatus(payment.getPaymentStatus())
	                .event(event)
	                .toStatus(next)
	                .actor(PaymentActor.SYSTEM) //TODO: fetch merchant context to identify actor
	                .occurredAt(LocalDateTime.now())
	                .build();
	        payment.setPaymentStatus(next);
	        paymentTransitionLogRepository.save(log);
	        return next;
	 }       

}

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 