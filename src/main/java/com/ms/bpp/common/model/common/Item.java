package com.ms.bpp.common.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Item {
	private String id;
	
	@JsonProperty("parent_item_id")
	private String parentItemId;
	
    private Descriptor descriptor;
    
    private String manufacturer;
    
    private Price price;
    
    @JsonProperty("category_ids")
    private List<String> categoryIds;
    
    @JsonProperty("fulfillment_ids")
    private List<String> fulfillmentIds;
    
    @JsonProperty("location_ids")
    private List<String> locationIds;
    
    @JsonProperty("payment_ids")
    private List<String> paymentIds;
    
    @JsonProperty("add_ons")
    private List<AddOn> addOns;
    
    private XInput xinput;
    
    private Time time; 
    
    private boolean rateable;
    
    private List<TagGroup> tags;
    private Quantity quantity;
}
