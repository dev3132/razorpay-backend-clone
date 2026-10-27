package com.razorpay.payment.gateway.dto;

import java.util.Map;
import java.util.UUID;

import com.razorpay.common.entity.Money;
import com.razorpay.common.enums.PaymentMethod;

public record PaymentRequest(
		UUID paymentId,
		UUID orderId,
		UUID merchantId,
		Money amount,
		PaymentMethod method,
		Map<String, Object> methodDetails
		) {

}
