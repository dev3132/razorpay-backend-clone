package com.razorpay.merchant.entity;

import java.util.UUID;

import com.razorpay.common.entity.BaseEntity;
import com.razorpay.common.enums.BusinessType;
import com.razorpay.common.enums.MerchantStatus;
import com.razorpay.common.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "app_user",
			indexes = {
					@Index(name = "idx_app_user_merchant_id", columnList = "merchant_id")
			})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id", nullable = false)
	private Merchant merchant;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String passwordHash;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role;
	
	
}


















