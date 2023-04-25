package com.ms.bpp.common.model.response;

import com.ms.bpp.common.model.common.Context;
import lombok.Data;

@Data
public class Response {
	private Context context;
	private ResponseMessage message;
	private Error error;
}
