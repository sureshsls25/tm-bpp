package com.ms.bpp.common.model.common;

import lombok.Data;

@Data
public class Time {
	private String label;
	private String timestamp;
	private String duration;
	private TimeRange range;
	private String days;
	private Schedule schedule;
}
