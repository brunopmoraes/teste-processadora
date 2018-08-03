package br.com.teste.processadora.utils;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.teste.processadora.model.CreditCard;

public class CreditCardUtils {

	public static CreditCard getCreditCardWithBalance() {
		return CreditCard.builder()
				.id(UUID.randomUUID())
				.number("1234567890982312")
				.approvedCredit(new BigDecimal("1000"))
				.balance(new BigDecimal("600"))
				.build();
	}
	
	public static CreditCard getCreditCardWithoutBalance() {
		return CreditCard.builder()
				.id(UUID.randomUUID())
				.number("1234123412341234")
				.approvedCredit(new BigDecimal("600"))
				.balance(new BigDecimal("0"))
				.build();
	}
	
}
