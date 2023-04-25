package com.ms.bpp.common.model.cancel;

import com.ms.bpp.common.model.common.Descriptor;
import lombok.Data;

@Data
public class CancelMessage {
	private String orderId;
	private String cancellationReasonId;
	private Descriptor descriptor;
}