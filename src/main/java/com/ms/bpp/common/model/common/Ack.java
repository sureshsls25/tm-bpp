package com.ms.bpp.common.model.common;

import com.ms.bpp.common.enums.AckStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ack {
	private AckStatus status;
}