package br.com.teste.processadora.dto.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.teste.processadora.model.enums.ActionTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

	private String action;
	
	@JsonProperty("cardnumber")
	private String cardNumber;
	
	private String amount;

	public BigDecimal getAmount() {
		return new BigDecimal(amount.replace(",", "."));
	}
	
	public ActionTransaction getAction() {
		return ActionTransaction.getActionByDescription(action);
	}
}
