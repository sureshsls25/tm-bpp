package com.ms.bpp.common.model.support;

import com.ms.bpp.common.model.common.Context;
import lombok.Data;

@Data
public class SupportRequest {
	private Context context;
	private SupportMessage message;
}
