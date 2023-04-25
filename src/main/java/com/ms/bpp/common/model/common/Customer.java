package com.ms.bpp.common.model.common;

import com.ms.bpp.common.dto.AddressDto;
import lombok.Data;

@Data
public class Customer {
	private Person person;
	private Contact contact;
	private AddressDto address;
}
