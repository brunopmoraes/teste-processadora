package br.com.teste.processadora.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import br.com.teste.processadora.dto.request.TransactionRequest;
import br.com.teste.processadora.model.CreditCard;
import br.com.teste.processadora.model.TransactionCreditCard;
import br.com.teste.processadora.model.enums.StatusCode;

@Service
public class WithdrawalTransactionService extends TransactionalService {

	@Override
	public TransactionCreditCard doTransaction(TransactionRequest transactionRequest) {

		TransactionCreditCard transactionCreditCard = TransactionCreditCard.builder()
														.action(transactionRequest.getAction())
														.amount(transactionRequest.getAmount())
														.authorizationCode("")
														.build();
		
		try {
			CreditCard creditCard = new CreditCard();//creditCardRepository.findByNumber(transactionRequest.getCardNumber());
			transactionCreditCard.setCreditCard(creditCard);
			
			if (isValidTransaction(transactionCreditCard)) {
				creditCard.subtractBalance(transactionCreditCard.getAmount());
				// TODO save transaction
				// TODO update credit card				
			}
			
			return transactionCreditCard;
		} catch (Exception ex) {
			transactionCreditCard.setCode(StatusCode.PROCESS_ERROR);
			return transactionCreditCard;
		}
	}

	private boolean isValidTransaction(TransactionCreditCard transactionCreditCard) {
		return isValidCreditCard(transactionCreditCard);		
	}

	private boolean isValidCreditCard(TransactionCreditCard transactionCreditCard) {
		
		if (Objects.isNull(transactionCreditCard.getCreditCard())) {
			transactionCreditCard.setCode(StatusCode.INVALID_COUNT);
			return false;
		}
		
		return isValidBalance(transactionCreditCard);
	}

	private boolean isValidBalance(TransactionCreditCard transactionCreditCard) {

		if (transactionCreditCard.getCreditCard().getBalance()
				.compareTo(transactionCreditCard.getAmount()) < 0) {
			transactionCreditCard.setCode(StatusCode.INSUFFICIENT_BALANCE);
			return false;
		}
		
		transactionCreditCard.setCode(StatusCode.APROVED);
		return true;
	}
}
