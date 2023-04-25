package com.ms.bpp.common.model.common;

import lombok.Data;

@Data
public class Stop {
	private String id;
	private String parent_stop_id;
	private Location location;
	private String type;
	private Time time;
	private Descriptor instructions;
	private Contact contact;
	private Person person;
	private Authorization authorization;
}
