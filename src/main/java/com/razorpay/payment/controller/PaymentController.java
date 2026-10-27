package com.razorpay.payment.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.payment.dto.request.PaymentInitRequest;
import com.razorpay.payment.dto.response.PaymentResponse;
import com.razorpay.payment.service.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
	
	private final PaymentService paymentService;
	UUID merchantId = UUID.fromString("");      //Auth Token
	
	@PostMapping
	public ResponseEntity<PaymentResponse> initiate(@Valid @RequestBody PaymentInitRequest request){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(paymentService.initiate(merchantId, request));
	}
	
	@PostMapping("/{paymentId}/capture")
	public ResponseEntity<PaymentResponse> capture(@PathVariable UUID paymentId){
		return ResponseEntity.ok(paymentService.capture(merchantId, paymentId));
	}
}



















