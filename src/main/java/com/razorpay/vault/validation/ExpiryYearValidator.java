package com.razorpay.vault.validation;

import java.time.Year;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExpiryYearValidator implements ConstraintValidator<ExpiryYear, Integer>{

	@Override
	public boolean isValid(Integer inputYear, ConstraintValidatorContext context) {
		if (inputYear ==null) {
            return false;
        }

        int currentYear = Year.now().getValue();

        return inputYear >= currentYear;
	}
	
	
}
