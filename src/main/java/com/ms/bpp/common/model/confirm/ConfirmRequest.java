package com.ms.bpp.common.model.confirm;

import com.ms.bpp.common.model.common.Context;
import lombok.Data;

@Data
public class ConfirmRequest {
	private Context context;
	private ConfirmMessage message;
}
