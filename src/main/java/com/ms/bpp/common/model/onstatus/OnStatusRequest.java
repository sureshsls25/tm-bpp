package com.ms.bpp.common.model.onstatus;

import com.ms.bpp.common.model.common.Context;
import lombok.Data;

@Data
public class OnStatusRequest {
	private Context context;
	private OnStatusMessage message;
	private Error error;
}