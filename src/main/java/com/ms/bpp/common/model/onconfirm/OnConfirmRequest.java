package com.ms.bpp.common.model.onconfirm;

import com.ms.bpp.common.model.common.Context;
import lombok.Data;

@Data
public class OnConfirmRequest {
	private Context context;
	private OnConfirmMessage message;
	private Error error;
}
