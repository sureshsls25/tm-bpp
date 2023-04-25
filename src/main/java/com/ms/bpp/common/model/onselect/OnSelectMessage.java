package com.ms.bpp.common.model.onselect;

import com.ms.bpp.common.model.common.Order;
import lombok.Data;

@Data
public class OnSelectMessage {
	private Order order;
}
