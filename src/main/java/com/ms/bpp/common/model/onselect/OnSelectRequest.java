package com.ms.bpp.common.model.onselect;

import com.ms.bpp.common.model.common.Context;
import lombok.Data;

@Data
public class OnSelectRequest {
	private Context context;
	private OnSelectMessage message;
	private Error error;
}