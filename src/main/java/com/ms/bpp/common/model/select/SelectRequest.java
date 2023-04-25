package com.ms.bpp.common.model.select;

import com.ms.bpp.common.model.common.Context;
import lombok.Data;

@Data
public class SelectRequest {
	private Context context;
	private SelectMessage message;
}
