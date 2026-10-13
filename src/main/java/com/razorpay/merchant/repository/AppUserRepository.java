package com.razorpay.merchant.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.razorpay.merchant.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {

	boolean existsByEmail(String email);

}
