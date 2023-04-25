package com.ms.bpp.common.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Category {
	private String id;
	
	@JsonProperty("parent_category_id")
	private String parentCategoryId;
	
	private Descriptor descriptor;
	
	private Time time;
	
	private List<TagGroup> tags;
}
