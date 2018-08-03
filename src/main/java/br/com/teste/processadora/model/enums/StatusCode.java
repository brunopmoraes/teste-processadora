package br.com.teste.processadora.model.enums;

public enum StatusCode {
	
	APROVED("00"),
	INSUFFICIENT_BALANCE("51"),
	INVALID_COUNT("14"),
	PROCESS_ERROR("96");
	
	private String code;
	
	private StatusCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
