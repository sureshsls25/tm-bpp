package com.ms.bpp.common.model.common;

import lombok.Data;

@Data 
public class Image {
	private String url;
	private String size_type; //Enum [xs, sm, md, lg, xl, custom]
	private String width;
	private String height;
}
