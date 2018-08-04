package br.com.teste.processadora.utils;

import br.com.teste.processadora.model.TransactionCreditCard;
import br.com.teste.processadora.model.enums.StatusCode;

public class TransactionCreditCardUtils {

	public static TransactionCreditCard getTransactionCreditCardApproved() {
		return TransactionCreditCard.builder().code(StatusCode.APROVED).build();
	}
	
	public static TransactionCreditCard getTransactionCreditCardInsufficientBalance() {
		return TransactionCreditCard.builder().code(StatusCode.INSUFFICIENT_BALANCE).build();
	}
}
