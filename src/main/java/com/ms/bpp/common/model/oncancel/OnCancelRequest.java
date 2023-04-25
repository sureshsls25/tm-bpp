package com.ms.bpp.common.model.oncancel;

import com.ms.bpp.common.model.common.Context;
import lombok.Data;

@Data
public class OnCancelRequest {
	private Context context;
	private OnCancelMessage message;
}