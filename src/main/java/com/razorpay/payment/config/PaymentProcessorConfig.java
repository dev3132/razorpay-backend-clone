package com.razorpay.payment.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.razorpay.common.enums.PaymentMethod;
import com.razorpay.payment.processor.PaymentProcessor;
import com.razorpay.payment.processor.strategy.CardPaymentProcessor;
import com.razorpay.payment.processor.strategy.NetBankingPaymentProcessor;
import com.razorpay.payment.processor.strategy.UpiPaymentProcessor;

@Configuration
public class PaymentProcessorConfig {
	
	@Bean
	public Map<PaymentMethod, PaymentProcessor> paymentProcessorMap(){
		return Map.of(
				PaymentMethod.CARD, new CardPaymentProcessor(),
				PaymentMethod.NETBANKING, new NetBankingPaymentProcessor(),
				PaymentMethod.UPI, new UpiPaymentProcessor()
				
				);
	}
}
