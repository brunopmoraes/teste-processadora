package br.com.teste.processadora.model.enums;

public enum Action {
	
	WITHDRAW("withdraw");
	
	private String description;
	
	private Action(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
