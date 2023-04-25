package com.ms.bpp.common.model.onsupport;

import com.ms.bpp.common.model.common.Context;
import lombok.Data;

@Data
public class OnSupportRequest {
	private Context context;
	private OnSupportMessage message;
}
