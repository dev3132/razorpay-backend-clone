package com.razorpay.payment.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.razorpay.common.enums.PaymentMethod;
import com.razorpay.payment.gateway.PaymentAdapter;
import com.razorpay.payment.gateway.adapters.CardPaymentAdapter;
import com.razorpay.payment.gateway.adapters.NetBankingAdapter;
import com.razorpay.payment.gateway.adapters.UpiPaymentAdapter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class PaymentAdapterConfig {
	
	private final CardPaymentAdapter cardPaymentAdapter;
	private final NetBankingAdapter netBankingAdapter;
	private final UpiPaymentAdapter upiPaymentAdapter;
	
	@Bean
	public Map<PaymentMethod, PaymentAdapter> paymentAdapterMap(){
		return Map.of(
				PaymentMethod.CARD, cardPaymentAdapter,
				PaymentMethod.NETBANKING, netBankingAdapter,
				PaymentMethod.UPI, upiPaymentAdapter
				);
	}
}
