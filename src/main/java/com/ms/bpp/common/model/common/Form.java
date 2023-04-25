package com.ms.bpp.common.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Form {
	@JsonProperty("url")
	private String url;
	
	@JsonProperty("data")
	private Object data;
	
	@JsonProperty("mime_type")
	private String mimeType;
	
	@JsonProperty("submission_id")
	private String submissionId;	//UUID
}
