package br.com.teste.processadora.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TransactionResponse {

	private String action;
	
	private String code;
	
	@JsonProperty("authorization_code")
	private String authorizationCode;	
}
