package br.com.teste.processadora.service;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.teste.processadora.exception.AuthorizationCodeGenerationException;
import br.com.teste.processadora.model.TransactionCreditCard;
import br.com.teste.processadora.repository.TransactionCreditCardRepository;
import br.com.teste.processadora.utils.TransactionCreditCardUtils;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionCreditCardServiceTest {

	@Autowired
	private TransactionCreditCardService transactionCreditCardService;
	
	@MockBean
	private TransactionCreditCardRepository transactionCreditCardRepository;
	
	@Before
	public void before() {
		when(transactionCreditCardRepository.save(any(TransactionCreditCard.class)))
			.then(new Answer<TransactionCreditCard>() {
				@Override
				public TransactionCreditCard answer(InvocationOnMock invocation) throws Throwable {
					return (TransactionCreditCard) invocation.getArguments()[0];
				}
			});
	}
	
	@Test
	public void testAuthorizationCodeGenerated() {
		when(transactionCreditCardRepository.findByAuthorizationCode(anyString())).thenReturn(null);
		
		try {
			TransactionCreditCard transactionCreditCard = transactionCreditCardService.save(
					TransactionCreditCardUtils.getTransactionCreditCardApproved());
			assertNotNull(transactionCreditCard.getAuthorizationCode());
			assertNotEquals("", transactionCreditCard.getAuthorizationCode());
		} catch (AuthorizationCodeGenerationException e) {
			fail(e.getMessage(), e);
		}		
	}
	
	@Test
	public void testAuthorizationCodeNotGeneratedBecauseStatusCode() {
		when(transactionCreditCardRepository.findByAuthorizationCode(anyString())).thenReturn(null);
		
		try {
			TransactionCreditCard transactionCreditCard = transactionCreditCardService.save(
					TransactionCreditCardUtils.getTransactionCreditCardInsufficientBalance());
			assertNull(transactionCreditCard.getAuthorizationCode());
		} catch (AuthorizationCodeGenerationException e) {
			fail(e.getMessage(), e);
		}	
	}
	
	@Test(expected = AuthorizationCodeGenerationException.class)
	public void testAuthorizationCodeException() throws AuthorizationCodeGenerationException {
		when(transactionCreditCardRepository.findByAuthorizationCode(anyString())).thenReturn(new TransactionCreditCard());		
		transactionCreditCardService.save(TransactionCreditCardUtils.getTransactionCreditCardApproved());
	}
	
}
