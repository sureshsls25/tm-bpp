package com.ms.bpp.common.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Context {
	private String domain;

	private Location location;
	
	private String action;
	
	@JsonProperty("version")
	private String version;
	
	@JsonProperty("bap_id")
	private String bapId;
	
	@JsonProperty("bap_uri")
	private String bapUri;
	
	@JsonProperty("bpp_id")
	private String bppId;
	
	@JsonProperty("bpp_uri")
	private String bppUri;
	
	@JsonProperty("transaction_id")
	private String transactionId;
	
	@JsonProperty("message_id")
	private String messageId;
	
	private String timestamp;
	private String key;
	private String ttl;

}