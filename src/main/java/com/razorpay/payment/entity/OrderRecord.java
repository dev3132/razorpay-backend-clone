package com.razorpay.payment.entity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.razorpay.common.entity.Money;
import com.razorpay.common.enums.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_records")
public class OrderRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	//no FK - cross-service boundary
	@Column(name = "merchant_id"   ,nullable = false)
	private UUID merchantId;
	
	@Embedded
	private Money amount;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private OrderStatus orderStatus = OrderStatus.CREATED;
	
	@Column(nullable = false)
	private Integer attempts;
	
	@JdbcTypeCode((SqlTypes.JSON))
	@Column(nullable = false, columnDefinition = "json")
	private Map<String, Object> notes;
	
	@Column(name = "expires_at", nullable = false)
	private LocalDateTime expiresAt;
}












