package br.com.teste.processadora.service;

import java.security.MessageDigest;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.teste.processadora.exception.AuthorizationCodeGenerationException;
import br.com.teste.processadora.model.TransactionCreditCard;
import br.com.teste.processadora.model.enums.StatusCode;
import br.com.teste.processadora.repository.TransactionCreditCardRepository;

@Service
@Transactional
public class TransactionCreditCardService {

	@Value("${application.max.attempt.authorization-code}")
	private Integer maxAttemptAuthorizationCode;
	
	@Autowired
	private TransactionCreditCardRepository transactionCreditCardRepository;
	
	@Autowired
	@Qualifier("sha256Bean")
	private MessageDigest sha256Digest;
	
	public TransactionCreditCard save(TransactionCreditCard transactionCreditCard) throws AuthorizationCodeGenerationException {
		
		if (transactionCreditCard.getCode().equals(StatusCode.APROVED)) {
			transactionCreditCard.setAuthorizationCode(generateNewAuthorizationCode());
		}
		
		return transactionCreditCardRepository.save(transactionCreditCard);
	}

	private String generateNewAuthorizationCode() throws AuthorizationCodeGenerationException {
		byte[] hash = sha256Digest.digest(UUID.randomUUID().toString().getBytes());
		String authorizationCode = StringUtils.left(new String(hash), 6);
		
		for (int i = 0; i < maxAttemptAuthorizationCode; i++) {
			TransactionCreditCard transactionCreditCard = transactionCreditCardRepository
																.findByAuthorizationCode(authorizationCode);
			
			if (Objects.isNull(transactionCreditCard)) {
				return authorizationCode;
			}
		}
		
		throw new AuthorizationCodeGenerationException();
	}
}
