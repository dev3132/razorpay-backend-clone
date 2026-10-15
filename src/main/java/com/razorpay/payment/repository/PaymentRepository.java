package com.razorpay.payment.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.razorpay.payment.entity.OrderRecord;
import com.razorpay.payment.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, UUID>{

	List<Payment> findByOrder_Id(OrderRecord order);

}
