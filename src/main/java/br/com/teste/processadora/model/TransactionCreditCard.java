package br.com.teste.processadora.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import br.com.teste.processadora.model.enums.ActionTransaction;
import br.com.teste.processadora.model.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionCreditCard {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private UUID id;

	private ActionTransaction action;
	
	private String authorizationCode;
	
	private StatusCode code;
	
	private BigDecimal amount;
    
	@ManyToOne
	private CreditCard creditCard;
}
