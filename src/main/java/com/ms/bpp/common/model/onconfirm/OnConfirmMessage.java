package com.ms.bpp.common.model.onconfirm;

import com.ms.bpp.common.model.common.Order;
import lombok.Data;

@Data
public class OnConfirmMessage {
	private Order order;
}
