package com.ms.bpp.common.model.update;

import com.ms.bpp.common.model.common.Context;
import lombok.Data;

@Data
public class UpdateRequest {
	private Context context;
	private UpdateMessage message;
}