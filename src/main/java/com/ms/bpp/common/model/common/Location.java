package com.ms.bpp.common.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Location {
	private String id;
	private Descriptor descriptor;
	private String map_url;
	private String gps;
	private String address;
	private City city;
	private String district;
	private State state;
	private Country country;
	private String area_code;
	private Circle circle;
	private String polygon;
	
	@JsonProperty("3dspace")
	private String threeDSpace;
	
	private String rating;
}


