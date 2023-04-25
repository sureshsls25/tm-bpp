package com.ms.bpp.common.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Scalar {
	private String type;
    private String value;
    
    @JsonProperty("estimated_value")
    private double estimatedValue;
    
    @JsonProperty("computed_value")
    private double computedValue;
    
    private Range range;
    private String unit;
}
