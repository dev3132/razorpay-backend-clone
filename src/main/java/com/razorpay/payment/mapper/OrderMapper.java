package com.razorpay.payment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.razorpay.payment.dto.response.OrderResponse;
import com.razorpay.payment.entity.OrderRecord;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
	
	OrderResponse toResponse(OrderRecord order);

}
