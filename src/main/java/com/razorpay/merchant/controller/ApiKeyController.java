package com.razorpay.merchant.controller;


import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.razorpay.merchant.dto.response.ApiKeyResponse;
import com.razorpay.merchant.service.ApiKeyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/merchants/{merchantId}/api-keys")
@RequiredArgsConstructor
public class ApiKeyController {
	
	private final ApiKeyService apiKeyService;
	
	@PostMapping
	public ResponseEntity<ApiKeyCreateResponse> create(@PathVariable UUID merchantId,
								    @Valid @RequestBody CreateApiKeyRequest request){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(apiKeyService.create(merchantId,request));
	}
	
	@GetMapping
	public ResponseEntity<List<ApiKeyResponse>> listByMerchant(@PathVariable UUID merchantId){
		return ResponseEntity.ok(apiKeyService.listByMerchant(merchantId));
	}
	
	@DeleteMapping
	public ResponseEntity<Void> revoke(@PathVariable UUID merchantId, @PathVariable UUID keyId){
		apiKeyService.revoke(merchantId, keyId);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/{keyId}/rotate")
	public ResponseEntity<ApiKeyCreateResponse> rotateKeyKey(@PathVariable UUID merchantId, @PathVariable UUID keyId){
		return ResponseEntity.ok(apiKeyService.rotate(merchantId, keyId));
	}
	
}
























