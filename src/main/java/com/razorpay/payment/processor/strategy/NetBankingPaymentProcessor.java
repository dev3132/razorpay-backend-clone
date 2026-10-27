package com.razorpay.payment.processor.strategy;

import org.springframework.stereotype.Component;

import com.razorpay.common.enums.PaymentMethod;
import com.razorpay.common.util.RandomizerUtil;
import com.razorpay.payment.gateway.adapters.CardPaymentAdapter;
import com.razorpay.payment.gateway.dto.PaymentResult;
import com.razorpay.payment.processor.PaymentProcessor;
import com.razorpay.payment.processor.PaymentProcessorRouter;
import com.razorpay.payment.processor.dto.PaymentProcessorRequest;
import com.razorpay.payment.processor.dto.PaymentProcessorResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NetBankingPaymentProcessor implements PaymentProcessor{
	

	
	@Override
	public PaymentProcessorResponse charge(PaymentProcessorRequest request) {
		
		final String BANK_CODE_FAIL = "BANK_CODE_FAIL";

        String bankCode = request.methodDetails() != null ?
                request.methodDetails().get("bank").toString() : null;

        // simulation
        if (BANK_CODE_FAIL.equals(bankCode)) {
            return new PaymentProcessorResponse.Failure("BANK_REJECTED",
                    "Banked rejected the transaction registration"
                    );
        }

        String processorRef = "NBK_PROCESSOR_"+ RandomizerUtil.randomBase64(16);

//        String redirectRef = "http://REDIRECT_BANK.com/"+processorRef;

        return new PaymentProcessorResponse.Pending(processorRef);
		
	}

}



















