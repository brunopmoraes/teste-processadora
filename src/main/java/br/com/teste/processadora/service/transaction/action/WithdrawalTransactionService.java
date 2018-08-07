package br.com.teste.processadora.service.transaction.action;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(WithdrawalTransactionService.class);
	
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
			
			if (isValidCreditCard(transactionCreditCard)) {
				String statusCode = creditCardRepository.executeWithdrawalTransaction(creditCard.getNumber(),
						transactionCreditCard.getAmount());
				transactionCreditCard.setCode(StatusCode.getStatusByCode(statusCode));
			}
			
			transactionCreditCardService.save(transactionCreditCard);
			
			return transactionCreditCard;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			transactionCreditCard.setCode(StatusCode.PROCESS_ERROR);
			return transactionCreditCard;
		}
	}

	private boolean isValidCreditCard(TransactionCreditCard transactionCreditCard) {
		
		if (Objects.isNull(transactionCreditCard.getCreditCard())) {
			transactionCreditCard.setCode(StatusCode.INVALID_COUNT);
			return false;
		}
		
		return true;
	}
}
