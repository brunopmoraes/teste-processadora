package br.com.teste.processadora.dto.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.teste.processadora.model.enums.Action;
import lombok.Data;

@Data
public class TransactionRequest {

	private String action;
	
	@JsonProperty("cardnumber")
	private String cardNumber;
	
	private String amount;

	public BigDecimal getAmount() {
		return new BigDecimal(amount.replace(",", "."));
	}
	
	public Action getAction() {
		return Action.getActionByDescription(action);
	}
}
