package com.razorpay.vault.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull.List;

@Target({ FIELD,PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ExpiryYearValidator.class })
public @interface ExpiryYear {
	
	String message() default "{Invalid Expiry year}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}




























