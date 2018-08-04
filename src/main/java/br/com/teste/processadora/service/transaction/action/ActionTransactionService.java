package br.com.teste.processadora.service.transaction.action;

import br.com.teste.processadora.dto.request.TransactionRequest;
import br.com.teste.processadora.model.TransactionCreditCard;

public interface ActionTransactionService {

	TransactionCreditCard executeAction(TransactionRequest transactionalRequest);

}
