package com.ms.bpp.common.model.cancel;

import com.ms.bpp.common.model.common.Context;
import lombok.Data;

@Data
public class CancelRequest {
	private Context context;
	private CancelMessage message;
}
