package com.razorpay.merchant.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.razorpay.merchant.dto.response.ApiKeyResponse;
import com.razorpay.merchant.entity.ApiKey;

public interface ApiKeyRepository extends JpaRepository<ApiKey, UUID> {

	List<ApiKey> findByMerchant_Id(UUID merchantId);

}
