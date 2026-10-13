package com.razorpay.merchant.service;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.razorpay.merchant.dto.request.MerchantSignUpRequest;
import com.razorpay.merchant.dto.response.MerchantResponse;

import jakarta.validation.Valid;


public interface AuthService {

	MerchantResponse signUp(MerchantSignUpRequest signUpRequest);
}
