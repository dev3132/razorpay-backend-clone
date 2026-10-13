package com.razorpay.merchant.dto.response;

import java.util.UUID;

import com.razorpay.common.enums.BusinessType;
import com.razorpay.common.enums.MerchantStatus;

public record MerchantResponse(
	UUID merchantId,
	String name,
	String email,
	String businessName,
	BusinessType businessType,
	MerchantStatus status
) {

}
