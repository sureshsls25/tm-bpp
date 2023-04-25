package com.ms.bpp.common.model.search;

import com.ms.bpp.common.model.common.Context;
import lombok.Data;

@Data
public class SearchRequest {
	private Context context;
	private SearchMessage message;
	//private Error error;
}
