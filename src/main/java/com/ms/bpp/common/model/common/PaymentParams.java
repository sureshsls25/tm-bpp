package com.ms.bpp.common.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentParams {
	@JsonProperty("transaction_id")
	private String transactionId;
	
	private String amount;
	private String currency;
	private String bank_code;
	private String bank_account_number;
	private String virtual_payment_address;
	private String source_bank_code;
	private String source_bank_account_number;
	private String source_virtual_payment_address;
}
