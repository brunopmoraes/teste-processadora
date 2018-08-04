package br.com.teste.processadora.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.processadora.model.TransactionCreditCard;

@Repository
public interface TransactionCreditCardRepository extends CrudRepository<TransactionCreditCard, UUID> {

	TransactionCreditCard findByAuthorizationCode(String authorizationCode);
	
}
