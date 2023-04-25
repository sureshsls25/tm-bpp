package com.ms.bpp.common.model.common;

import lombok.Data;

@Data
public class Cancellation {
	
	private String time;
	private String cancelled_by;
	private Option reason;
	private Descriptor additional_description;
}
