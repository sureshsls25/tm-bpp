package com.ms.bpp.common.model.common;

import lombok.Data;

@Data
public class CancellationTerm {

	private FulfillmentState fulfillment_state;
	private boolean reason_required;
	private Time cancel_by;
	private Fee cancellation_fee;
	private XInput xinput;
	private MediaFile external_ref;
	
}
