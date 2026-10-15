package com.razorpay.merchant.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.razorpay.merchant.dto.response.ApiKeyResponse;
import com.razorpay.merchant.entity.ApiKey;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApiKeyMapper {
	
	ApiKeyCreateResponse toCreateResponse(ApiKey apiKey);
	
	List<ApiKeyResponse> toListResponse(List<ApiKey> apiKeyList);
}
