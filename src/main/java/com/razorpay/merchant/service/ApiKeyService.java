package com.razorpay.merchant.service;

import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;

import com.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.razorpay.merchant.dto.response.ApiKeyResponse;



public interface ApiKeyService {

	ApiKeyCreateResponse create(UUID merchantId,CreateApiKeyRequest request);

	List<ApiKeyResponse> listByMerchant(UUID merchantId);

	void revoke(UUID merchantId, UUID keyId);

	ApiKeyCreateResponse rotate(UUID merchantId, UUID keyId);

}
