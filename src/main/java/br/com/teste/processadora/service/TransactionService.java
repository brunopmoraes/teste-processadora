package br.com.teste.processadora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import br.com.teste.processadora.dto.request.TransactionRequest;
import br.com.teste.processadora.model.TransactionCreditCard;

@Service
public class TransactionService {

	@Autowired
	private ApplicationContext applicationContext;
	
	public TransactionCreditCard executeDoTransaction(TransactionRequest transactionRequest) {
		ActionTransactionService transactionalService = applicationContext.getBean(transactionRequest.getAction()
																					.getClazzTransaction());
		return transactionalService.executeAction(transactionRequest);
	}
	
}
