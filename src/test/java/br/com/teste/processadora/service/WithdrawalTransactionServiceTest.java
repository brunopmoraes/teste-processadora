package br.com.teste.processadora.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
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
import br.com.teste.processadora.repository.TransactionCreditCardRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WithdrawalTransactionServiceTest {

//	@Autowired
//	private WithdrawalTransactionService withdrawalTransactionService;
//	
//	@MockBean
//	private TransactionCreditCardRepository transactionCreditCardRepository;
	
//	@BeforeClass
	public void beforeClass() {
//		when(transactionCreditCardRepository.save(any())).thenAnswer(new Answer<TransactionCreditCard>() {
//			@Override
//			public TransactionCreditCard answer(InvocationOnMock invocation) throws Throwable {
//				return (TransactionCreditCard) invocation.getArguments()[0];
//			}
//		});
	}
	
	@Test
	public void testTransactionalApproved() {
		System.out.println("Teste");
//		withdrawalTransactionService.executeAction(
//				TransactionRequest.builder()
//					.action(ActionTransaction.WITHDRAW.getDescription())
//					.amount("200,98")
//					.cardNumber("1234567890982312")
//					.build());
	}
	
	public void testTransactionalInsufficientBalance() {
		
	}
	
	public void testTransactionalInvalidCount() {
		
	}
	
	public void testTransactionalProcessError() {
		
	}
}
