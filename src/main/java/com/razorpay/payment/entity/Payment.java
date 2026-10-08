package com.razorpay.payment.entity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.razorpay.common.entity.Money;
import com.razorpay.common.enums.PaymentMethods;
import com.razorpay.common.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "order_record_id", nullable = false)
	private OrderRecord orderRecord;
	
	@Column(name = "merchant_id", nullable = false)
	private UUID merchantId;
	
	@Column(name = "amount", nullable = false)
	@Embedded
	private Money amount;
	
	@Column(nullable = false, unique = true)
	private String idempotencyKey;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private PaymentMethods paymentMethod;
	
	@JdbcTypeCode((SqlTypes.JSON))
	@Column(nullable = false, columnDefinition = "json")
	private Map<String, Object> methodDetails;
	
	@Column(length = 100)
	private String bankReference;
	
	@Column(length = 100)
	private String errorCode;
	
	@Column(length = 255)
	private String errorDescription;
	
	private LocalDateTime authorizedAt;
	
	private LocalDateTime capturedAt;
	
	private LocalDateTime failedAt;
	
	private LocalDateTime refundedAt;
	
	private LocalDateTime settledAt;
}














