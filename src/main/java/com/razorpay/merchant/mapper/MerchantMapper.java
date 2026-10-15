package com.razorpay.merchant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.razorpay.merchant.dto.request.MerchantSignUpRequest;
import com.razorpay.merchant.dto.response.MerchantResponse;
import com.razorpay.merchant.entity.Merchant;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MerchantMapper {
	
	Merchant toEntityFromSignUpRequest(MerchantSignUpRequest request);
	
	MerchantResponse toResponse(Merchant merchant);
}
