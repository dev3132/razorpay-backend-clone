package com.razorpay.merchant.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.razorpay.common.enums.Environment;

import jakarta.persistence.Column;
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
@Table(name = "api_keys")
public class ApiKey {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "merchant_id", nullable = false)
	private Merchant merchant;
	
	@Column(nullable = false, length = 255, unique = true)
	private String keyId;
	
	@Column(nullable = false, length = 255)
	private String KeySecretHash;
	
	@Column(nullable = false, length = 255)
	@Enumerated(EnumType.STRING)
	private Environment environment;
	
	@Column(nullable = false)
	private boolean enabled = true;
	
	
	private LocalDateTime lastUsedAt;
	private LocalDateTime rotatedAt;
	private LocalDateTime gracePeriodExpiresAt;
	

}
















