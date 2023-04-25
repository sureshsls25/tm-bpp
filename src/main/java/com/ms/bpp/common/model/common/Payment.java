package com.ms.bpp.common.model.common;

import lombok.Data;

import java.util.List;

@Data
public class Payment {
	private String id;
	private String collected_by;
	private String url;
	private PaymentParams params;
	private String type; //Enum [ PRE-ORDER, PRE-FULFILLMENT, ON-FULFILLMENT, POST-FULFILLMENT ]
	private String status; //Enum [ PAID, NOT-PAID ]
	private Time time;
	private List<TagGroup> tags;
}

