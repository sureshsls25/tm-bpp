package com.ms.bpp.common.model.common;

import com.ms.bpp.common.enums.OrderStatus;
import lombok.Data;

import java.util.List;

@Data
public class Fulfillment {
	private String id;
	private String type;
	private List<String> language;
	private OrderState state;
	private boolean tracking;
	private Customer customer;
	private Agent agent;
	private Contact contact;
	private List<Stop> stops;
	private String path;
	private List<TagGroup> tags;
	private Time time;
	private OrderStatus status;
}
