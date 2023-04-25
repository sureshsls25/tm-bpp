package com.ms.bpp.common.model.onstatus;

import com.ms.bpp.common.model.common.Order;
import lombok.Data;

import java.util.List;

@Data
public class OnStatusMessage {
	private List<Order> order;
}
