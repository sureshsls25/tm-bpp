package com.ms.bpp.common.model.onupdate;

import com.ms.bpp.common.model.common.Order;
import lombok.Data;

@Data
public class OnUpdateMessage {
	private Order order;
}
