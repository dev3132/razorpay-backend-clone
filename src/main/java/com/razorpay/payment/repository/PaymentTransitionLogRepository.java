package com.razorpay.payment.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.razorpay.payment.entity.PaymentTransitionLog;

public interface PaymentTransitionLogRepository extends JpaRepository<PaymentTransitionLog, UUID>{

}
