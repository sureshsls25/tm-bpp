package com.ms.bpp.common.model.common;

import lombok.Data;

@Data
public class Authorization {

	private String type;
	private String token;
	private String valid_from;
	private String valid_to;
	private String status;
			
}
