package com.ms.bpp.common.model.oncancel;

import com.ms.bpp.common.model.common.Order;
import lombok.Data;

@Data
public class OnCancelMessage {
	private Order order;
}
