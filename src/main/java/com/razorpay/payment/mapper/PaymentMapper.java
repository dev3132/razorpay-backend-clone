package com.razorpay.payment.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.razorpay.payment.dto.response.PaymentResponse;
import com.razorpay.payment.entity.Payment;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {
	
	@Mapping(target = "orderId", source = "order.id")
	PaymentResponse toResponse(Payment payment);
	
	@Mapping(target = "orderId", source = "order.id")
	List<PaymentResponse> toListResponse(List<Payment> paymentList);
}
