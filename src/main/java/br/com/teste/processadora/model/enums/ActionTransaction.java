package br.com.teste.processadora.model.enums;

import java.util.Arrays;

import br.com.teste.processadora.service.transaction.action.ActionTransactionService;
import br.com.teste.processadora.service.transaction.action.WithdrawalTransactionService;

public enum ActionTransaction {
	
	WITHDRAW("withdraw", WithdrawalTransactionService.class);
	
	private String description;
	
	private Class<? extends ActionTransactionService> clazzTransactionService;
	
	private ActionTransaction(String description, Class<? extends ActionTransactionService> clazzTransactionService) {
		this.description = description;
		this.clazzTransactionService = clazzTransactionService;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Class<? extends ActionTransactionService> getClazzTransaction() {
		return clazzTransactionService;
	}
	
	public static ActionTransaction getActionByDescription(String description) {
		for (ActionTransaction action : Arrays.asList(ActionTransaction.values())) {
			if (action.getDescription().equals(description)) {
				return action;
			}
		};
		return null;
	}
}
