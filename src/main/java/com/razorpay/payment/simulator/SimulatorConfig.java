package com.razorpay.payment.simulator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.razorpay.common.enums.ChaosMode;
import com.razorpay.common.enums.PaymentMethod;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "payment.simulator")
@Getter
@Setter
public class SimulatorConfig {

	private Integer pollIntervalMs = 2000;
	private ChaosMode chaosMode = ChaosMode.NORMAL;
	private Map<String, MethodSimulatorConfig> methods = new HashMap<>();
	
	public SimulatorConfig.MethodSimulatorConfig configFor(PaymentMethod method) {
        return methods.getOrDefault(method.name(), new MethodSimulatorConfig());
    }
	
	public MethodSimulatorConfig get() {
		return null;
	}

    @Getter
    @Setter
    public static  class MethodSimulatorConfig{
        private Integer minDelaySeconds = 1;
        private Integer maxDelaySeconds = 5;
        private Integer successRate = 80;
    }
	
}
