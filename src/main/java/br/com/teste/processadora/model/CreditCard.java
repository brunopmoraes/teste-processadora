package br.com.teste.processadora.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

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
@NamedStoredProcedureQuery(name = "sp_withdrawal_transaction", procedureName = "sp_withdrawal_transaction",
	parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "credit_card_number", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "amount", type = BigDecimal.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "status", type = String.class)
	}
)
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
