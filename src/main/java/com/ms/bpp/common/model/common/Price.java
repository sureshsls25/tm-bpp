package com.ms.bpp.common.model.common;

import lombok.Data;

@Data
public class Price {
	private String currency;
	private String value;
	private String estimated_value;
	private String computed_value;
	private String listed_value;
	private String offered_value;
	private String minimum_value;
	private String maximum_value;
}
