package com.razorpay.vault.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.razorpay.vault.entity.VaultCard;

public interface VaultCardRepository extends JpaRepository<VaultCard, UUID>{

}
