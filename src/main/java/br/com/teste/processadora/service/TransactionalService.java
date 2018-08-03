package br.com.teste.processadora.service;

import br.com.teste.processadora.dto.request.TransactionRequest;
import br.com.teste.processadora.model.TransactionCreditCard;

public abstract class TransactionalService {

	public abstract TransactionCreditCard doTransaction(TransactionRequest transactionalDTO);

}
