package com.ms.bpp.common.model.common;

import lombok.Data;

@Data
public class Billing {
	private String name;
	private Organization organization;
	private String address;
	private State state;
	private City city;
	private String email;
	private String phone;
	private Time time;
	private String tax_id;
}
