package com.ms.bpp.common.model.onsearch;

import com.ms.bpp.common.model.common.Context;
import lombok.Data;

@Data
public class OnSearchRequest {
	private Context context;
	private OnSearchMessage message;
	private Error error;
}
