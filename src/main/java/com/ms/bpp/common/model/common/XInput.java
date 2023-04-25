package com.ms.bpp.common.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class XInput {
	@JsonProperty("form")
	private Form form;
	
	private boolean required;
	
	private Object form_submission;
}
