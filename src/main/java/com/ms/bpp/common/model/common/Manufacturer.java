package com.ms.bpp.common.model.common;

import lombok.Data;

@Data
public class Manufacturer {
	private Descriptor descriptor;
	private String address;
	private State state;
	private City city;
	private Contact contact;
}
