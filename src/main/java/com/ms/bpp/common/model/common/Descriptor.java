package com.ms.bpp.common.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Descriptor {
	
    private String code;
    private String name;
    
    @JsonProperty("short_desc")
    private String shortDesc;
    
    @JsonProperty("long_desc")
    private String longDesc;
    
    @JsonProperty("additional_desc")
    private AdditionalDesc additionalDesc;
    
    private List<MediaFile> media;
    
    private List<Image> images;
}
