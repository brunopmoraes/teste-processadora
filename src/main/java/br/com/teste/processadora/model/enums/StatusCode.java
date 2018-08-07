package br.com.teste.processadora.model.enums;

import java.util.Arrays;

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
	
	public static StatusCode getStatusByCode(String code) {
		for (StatusCode statusCode : Arrays.asList(StatusCode.values())) {
			if (statusCode.getCode().equals(code)) {
				return statusCode;
			}
		}
		return null;
	}
}
