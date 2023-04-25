package com.ms.bpp.common.model.oninit;

import com.ms.bpp.common.model.common.Context;
import lombok.Data;

@Data
public class OnInitRequest {
	private Context context;
	private OnInitMessage message;
	private Error error;
}