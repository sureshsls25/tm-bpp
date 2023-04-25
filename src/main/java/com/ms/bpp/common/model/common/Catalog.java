package com.ms.bpp.common.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Catalog {
	@JsonProperty("descriptor")
	private Descriptor bppDescriptor;

	/*@JsonProperty("categories")
	private Set<Category> bppCategories;*/

	@JsonProperty("fulfillments")
	private List<Fulfillment> bppFulfillments;

	@JsonProperty("payments")
	private List<Payment> bppPayments;
	
	@JsonProperty("offers")
	private List<Offer> bppOffers;

	@JsonProperty("providers")
	private List<Provider> bppProviders;

	private String exp;
	
	private String ttl;
	private String noResult;
}
