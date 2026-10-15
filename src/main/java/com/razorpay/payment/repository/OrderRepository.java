package com.razorpay.payment.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.razorpay.payment.entity.OrderRecord;

import jakarta.validation.constraints.Size;

public interface OrderRepository extends JpaRepository<OrderRecord, UUID> {

	boolean existsByMerchantIdAndReceipt(UUID merchantId, String receipt);

	Optional<OrderRecord> findByIdAndMerchantId(UUID merchantId, UUID orderId);

}
