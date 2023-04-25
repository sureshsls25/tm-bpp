package com.ms.bpp.common.model.common;

import lombok.Data;

@Data
public class MediaFile {

	private String mimetype;
	private String url;
	private String signature;
	private String dsa;
}
