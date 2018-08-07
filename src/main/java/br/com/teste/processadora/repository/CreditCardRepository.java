package br.com.teste.processadora.repository;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.teste.processadora.model.CreditCard;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard, UUID> {

	CreditCard findByNumber(String number);

	@Procedure(name = "sp_withdrawal_transaction")
	String executeWithdrawalTransaction(@Param("credit_card_number") String creditCardNumber,
						@Param("amount") BigDecimal amount);
}
