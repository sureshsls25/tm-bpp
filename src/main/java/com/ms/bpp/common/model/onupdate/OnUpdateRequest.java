package com.ms.bpp.common.model.onupdate;

import com.ms.bpp.common.model.common.Context;
import lombok.Data;

@Data
public class OnUpdateRequest {
	private Context context;
	private OnUpdateMessage message;
}