package br.com.teste.processadora.model.enums;

import java.util.Arrays;

public enum Action {
	
	WITHDRAW("withdraw");
	
	private String description;
	
	private Action(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Action getActionByDescription(String description) {
		for (Action action : Arrays.asList(Action.values())) {
			if (action.getDescription().equals(description)) {
				return action;
			}
		};
		return null;
	}
}
