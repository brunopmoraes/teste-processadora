package br.com.teste.processadora.service.transaction.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.teste.processadora.dto.request.TransactionRequest;
import br.com.teste.processadora.model.TransactionCreditCard;
import br.com.teste.processadora.model.enums.ActionTransaction;
import br.com.teste.processadora.model.enums.StatusCode;
import br.com.teste.processadora.repository.CreditCardRepository;
import br.com.teste.processadora.repository.TransactionCreditCardRepository;
import br.com.teste.processadora.service.transaction.action.WithdrawalTransactionService;
import br.com.teste.processadora.utils.CreditCardUtils;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WithdrawalTransactionServiceTest {

	@Autowired
	private WithdrawalTransactionService withdrawalTransactionService;
	
	@MockBean
	private CreditCardRepository creditCardRepository;
	
	@MockBean
	private TransactionCreditCardRepository transactionCreditCardRepository;
	
	@Before
	public void beforeClass() {
		when(transactionCreditCardRepository.save(any(TransactionCreditCard.class)))
			.thenAnswer(new Answer<TransactionCreditCard>() {
				@Override
				public TransactionCreditCard answer(InvocationOnMock invocation) throws Throwable {
					return (TransactionCreditCard) invocation.getArguments()[0];
				}
			});
		when(transactionCreditCardRepository.findByAuthorizationCode(anyString())).thenReturn(null);
	}
	
	@Test
	public void testTransactionalApproved() {
		when(creditCardRepository.findByNumber(anyString())).thenReturn(CreditCardUtils.getCreditCardWithBalance());
		
		TransactionCreditCard transactionCreditCard = withdrawalTransactionService.executeAction(
				TransactionRequest.builder()
					.action(ActionTransaction.WITHDRAW.getDescription())
					.amount("200,98")
					.cardNumber("1234567890982312")
					.build());
		
		assertTrue(transactionCreditCard.getCreditCard().getBalance().compareTo(new BigDecimal("399.02")) == 0);
		assertEquals(transactionCreditCard.getCode(), StatusCode.APROVED);
	}
	
	@Test
	public void testTransactionalInsufficientBalance() {
		when(creditCardRepository.findByNumber(anyString())).thenReturn(CreditCardUtils.getCreditCardWithoutBalance());
		
		TransactionCreditCard transactionCreditCard = withdrawalTransactionService.executeAction(
				TransactionRequest.builder()
					.action(ActionTransaction.WITHDRAW.getDescription())
					.amount("100")
					.cardNumber("1234123412341234")
					.build());
		
		assertTrue(transactionCreditCard.getCreditCard().getBalance().compareTo(BigDecimal.ZERO) == 0);
		assertEquals(transactionCreditCard.getCode(), StatusCode.INSUFFICIENT_BALANCE);
	}
	
	@Test
	public void testTransactionalInvalidCount() {
		when(creditCardRepository.findByNumber(anyString())).thenReturn(null);
		
		TransactionCreditCard transactionCreditCard = withdrawalTransactionService.executeAction(
				TransactionRequest.builder()
					.action(ActionTransaction.WITHDRAW.getDescription())
					.amount("100")
					.cardNumber("2345234523452345")
					.build());
		
		assertEquals(transactionCreditCard.getCreditCard(), null);
		assertEquals(transactionCreditCard.getCode(), StatusCode.INVALID_COUNT);
	}
	
	@Test
	public void testTransactionalProcessError() {
		when(creditCardRepository.findByNumber(anyString())).thenThrow(new NullPointerException());
		
		TransactionCreditCard transactionCreditCard = withdrawalTransactionService.executeAction(
				TransactionRequest.builder()
					.action(ActionTransaction.WITHDRAW.getDescription())
					.amount("100")
					.cardNumber("2345234523452345")
					.build());
		
		assertEquals(transactionCreditCard.getCode(), StatusCode.PROCESS_ERROR);
	}
}
