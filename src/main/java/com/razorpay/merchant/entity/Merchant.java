package com.razorpay.merchant.entity;

import java.util.UUID;

import com.razorpay.common.enums.BusinessType;
import com.razorpay.common.enums.MerchantStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "merchant")
public class Merchant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(nullable = false, unique = true, length = 50)
	private String email;
	
	@Column(nullable = false, length = 10)
	private String contactNumber;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private BusinessType businessType;
	
	@Column(length = 100)
	private String businessName;
	
	@Column(length = 200)
	private String websiteUrl;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private MerchantStatus status = MerchantStatus.PENDING_KYC;
	
	@Column(length = 20)
	private String gstNumber;
	
	@Column(length = 20)
	private String panNumber;
	
	@Column(length = 20)
	private String settlementBankAccountNumber;
	
	@Column(length = 20)
	private String settlementBankIfsc;
	
	@Column(length = 100)
	private String settlementBankAccountHolderName;
	
}





















