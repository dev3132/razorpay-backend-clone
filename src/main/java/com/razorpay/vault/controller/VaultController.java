package com.razorpay.vault.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.vault.dto.request.TokenizeRequest;
import com.razorpay.vault.dto.response.TokenizeResponse;
import com.razorpay.vault.service.VaultService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/vault")
@RequiredArgsConstructor
public class VaultController {
	
	private final VaultService vaultService;
	UUID merchantId = null; //TODO replace with merchant context
	
	@PostMapping("/tokenize")
    public ResponseEntity<TokenizeResponse> tokenize(@Valid @RequestBody TokenizeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vaultService.tokenize(request, merchantId));
    }
}




















