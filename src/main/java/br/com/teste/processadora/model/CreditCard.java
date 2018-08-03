package br.com.teste.processadora.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
public class CreditCard {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Getter
	@Setter
	private UUID id;
	
	@Getter
	@Setter
	private String number;
	
	@Getter
	@Setter
	private BigDecimal approvedCredit;
	
	@Getter	
	private BigDecimal balance;
	
	public void subtractBalance(BigDecimal amount) {
		this.balance = balance.subtract(amount);
	}	
}
