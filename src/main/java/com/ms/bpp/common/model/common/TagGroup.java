package com.ms.bpp.common.model.common;

import lombok.Data;

import java.util.List;

@Data
public class TagGroup {
	private boolean display = true;
	private Descriptor descriptor;
	private List<Tag> list;
}
