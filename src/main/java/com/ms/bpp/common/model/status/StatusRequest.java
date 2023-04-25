package com.ms.bpp.common.model.status;

import com.ms.bpp.common.model.common.Context;
import lombok.Data;

@Data
public class StatusRequest {
	private Context context;
	private StatusMessage message;
}
