package com.ms.bpp.common.model.common;

import lombok.Data;

@Data
public class Tag {
	private Descriptor descriptor;
	private String value;
	private boolean display;
}
