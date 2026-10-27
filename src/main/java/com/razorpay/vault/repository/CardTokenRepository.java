package com.razorpay.vault.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.razorpay.vault.entity.CardToken;

public interface CardTokenRepository extends JpaRepository<CardToken, UUID>{

	Optional<CardToken> findByTokenAndRevokedAtIsNull(String token);
	
	
}
