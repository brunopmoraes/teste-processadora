package br.com.teste.processadora.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {

	@Id
	@GeneratedValue(generator = "pg-uuid")
	@GenericGenerator(name = "pg-uuid", strategy = "uuid2")
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
