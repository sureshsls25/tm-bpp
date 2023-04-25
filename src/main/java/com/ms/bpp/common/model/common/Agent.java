package com.ms.bpp.common.model.common;

import com.ms.bpp.common.dto.AddressDto;
import lombok.Data;

@Data
public class Agent {
	private Person person;
    private Contact contact;
    private Organization organization;
    private AddressDto address;
}
