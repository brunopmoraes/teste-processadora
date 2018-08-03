package br.com.teste.processadora.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.processadora.model.CreditCard;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard, UUID> {

	CreditCard findByNumber(String number);
	
}
