package com.ms.bpp.common.model.common;

import lombok.Data;

@Data
public class Credential {
	private String id;
	private String type = "VerifiableCredential";
	private String url;
}
