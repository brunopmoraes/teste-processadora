package br.com.teste.processadora.service.transaction.action;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.teste.processadora.dto.request.TransactionRequest;
import br.com.teste.processadora.model.CreditCard;
import br.com.teste.processadora.model.TransactionCreditCard;
import br.com.teste.processadora.model.enums.StatusCode;
import br.com.teste.processadora.repository.CreditCardRepository;
import br.com.teste.processadora.service.TransactionCreditCardService;

@Service
@Transactional
public class WithdrawalTransactionService implements ActionTransactionService {

	@Autowired
	private CreditCardRepository creditCardRepository;
	
	@Autowired
	private TransactionCreditCardService transactionCreditCardService;
	
	@Override
	public TransactionCreditCard executeAction(TransactionRequest transactionRequest) {

		TransactionCreditCard transactionCreditCard = TransactionCreditCard.builder()
														.action(transactionRequest.getAction())
														.amount(transactionRequest.getAmount())
														.build();
		try {
			CreditCard creditCard = creditCardRepository.findByNumber(transactionRequest.getCardNumber());
			transactionCreditCard.setCreditCard(creditCard);
			
			if (isValidTransaction(transactionCreditCard)) {
				creditCard.subtractBalance(transactionCreditCard.getAmount());
				creditCardRepository.save(creditCard);
			}
			
			transactionCreditCardService.save(transactionCreditCard);
			
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
