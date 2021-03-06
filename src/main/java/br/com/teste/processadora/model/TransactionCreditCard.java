package br.com.teste.processadora.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

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
	@GeneratedValue(generator = "pg-uuid")
	@GenericGenerator(name = "pg-uuid", strategy = "uuid2")
	private UUID id;

	@Enumerated(EnumType.STRING)
	private ActionTransaction action;
	
	private String authorizationCode;
	
	@Enumerated(EnumType.STRING)
	private StatusCode code;
	
	private BigDecimal amount;
    
	private LocalDateTime dtCreated;
	
	@ManyToOne
	private CreditCard creditCard;
	
	@PrePersist
	public void beforePersist() {
		this.dtCreated = LocalDateTime.now();
	}
	
}
