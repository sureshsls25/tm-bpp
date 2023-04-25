package com.ms.bpp.common.model.common;

import lombok.Data;

import java.util.List;

@Data
public class Intent {
	private Descriptor descriptor;
	private Provider provider;
	private Fulfillment fulfillment;
	private Payment payment;
	private Category category;
	private Offer offer;
	private Item item;
	private List<TagGroup> tags;
	private Agent agent;

}
