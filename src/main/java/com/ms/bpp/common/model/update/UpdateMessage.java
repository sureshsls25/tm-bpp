package com.ms.bpp.common.model.update;

import com.ms.bpp.common.model.common.Order;
import lombok.Data;

@Data
public class UpdateMessage {
	private String updateTarget;
	private Order order;
}
