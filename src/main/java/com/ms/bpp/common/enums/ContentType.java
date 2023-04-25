package com.ms.bpp.common.enums;

public enum ContentType {

	TEXT_PLAIN("text/plain"),
	TEXT_HTML("text/html"),
	APPLICATION_JSON("application/json");

	private final String value;

	ContentType(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}