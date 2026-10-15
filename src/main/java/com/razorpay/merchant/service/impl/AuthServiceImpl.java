package com.razorpay.merchant.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.razorpay.common.enums.MerchantStatus;
import com.razorpay.common.enums.UserRole;
import com.razorpay.common.exception.DuplicateResourceException;
import com.razorpay.merchant.dto.request.MerchantSignUpRequest;
import com.razorpay.merchant.dto.response.MerchantResponse;
import com.razorpay.merchant.entity.AppUser;
import com.razorpay.merchant.entity.Merchant;
import com.razorpay.merchant.mapper.MerchantMapper;
import com.razorpay.merchant.repository.AppUserRepository;
import com.razorpay.merchant.repository.MerchantRepository;
import com.razorpay.merchant.service.AuthService;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final AppUserRepository appUserRepository;
	private final MerchantRepository merchantRepository;
	private final MerchantMapper merchantMapper;
	
	@Override
//	@Transactional
	public MerchantResponse signUp(MerchantSignUpRequest request) {

		if (appUserRepository.existsByEmail(request.email())) {
			throw new DuplicateResourceException("DUPLICATE_MERCHANT",
					"Email already exists"+request.email());
		}
		
		Merchant merchant = merchantMapper.toEntityFromSignUpRequest(request);
		merchant.setStatus(MerchantStatus.PENDING_KYC);
		
		merchant = merchantRepository.save(merchant);
		
		AppUser appUser = AppUser.builder()
				.email(request.email())
				.passwordHash(request.password())  // to do hash the password
				.merchant(merchant)
				.role(UserRole.OWNER)
				.build();
		
		appUser = appUserRepository.save(appUser);
		
		return new MerchantResponse(merchant.getId(), merchant.getName(), merchant.getEmail(),
				merchant.getBusinessName(),merchant.getBusinessType(),merchant.getStatus());
		 
	}

}


















