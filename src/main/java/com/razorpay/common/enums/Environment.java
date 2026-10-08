package com.razorpay.common.enums;

public enum Environment {
		SANDBOX("sandbox"),
		TEST("test"),
	LIVE("live");

	private final String value;

	Environment(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
