package br.com.teste.processadora.activator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.teste.processadora.dto.request.TransactionRequest;
import br.com.teste.processadora.dto.response.TransactionResponse;
import br.com.teste.processadora.model.TransactionCreditCard;
import br.com.teste.processadora.service.transaction.TransactionService;

@Component
public class TransactionActivator {

	private static final Logger logger = LoggerFactory.getLogger(TransactionActivator.class);
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private ObjectMapper mapper;
	
	@ServiceActivator(inputChannel = "serviceChannel")
    public String service(String body) {
		try {
			TransactionRequest transactionRequest = mapper.reader().forType(TransactionRequest.class).readValue(body);

			TransactionCreditCard transactionCreditCard = transactionService.executeDoTransaction(transactionRequest);

			TransactionResponse transactionResponse = TransactionResponse.builder()
															.action(transactionRequest.getAction().getDescription())
															.authorizationCode(transactionCreditCard.getAuthorizationCode())
															.code(transactionCreditCard.getCode().getCode()).build();

			return mapper.writeValueAsString(transactionResponse);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "Ocorreu um erro durante a transação.";
		}
	}
}
