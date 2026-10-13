package com.razorpay.merchant.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.razorpay.merchant.entity.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
	

}
