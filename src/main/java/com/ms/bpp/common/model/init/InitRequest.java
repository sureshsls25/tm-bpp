package com.ms.bpp.common.model.init;

import com.ms.bpp.common.model.common.Context;
import lombok.Data;

@Data
public class InitRequest {
	private Context context;
	private InitMessage message;
}
