package com.ms.bpp.common.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Offer {
	private String id;
	private Descriptor descriptor;
	
	@JsonProperty("location_ids")
	private List<String> locationIds;
	
	@JsonProperty("category_ids")
	private List<String> categoryIds;
	
	@JsonProperty("item_ids")
	private List<String> itemIds;
	
	private Time time;
	
	private List<TagGroup> tags;
}
