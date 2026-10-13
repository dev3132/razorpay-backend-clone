package com.razorpay.merchant.dto.request;

import com.razorpay.common.enums.BusinessType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MerchantSignUpRequest(
	
	@NotNull(message = "Name should be provided")	
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	String name,
	
	@Email
	@NotNull(message = "Email is required")
	String email,
	
	@NotNull(message = "Password is required")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	String password,
	
	@Size(max = 50, message = "Business name must be less than 50 characters")
	String businessName,
	
	BusinessType businessType
) {

}
