package br.com.teste.processadora.exception;

public class AuthorizationCodeGenerationException extends Exception {

	private static final long serialVersionUID = -3739852690370651493L;

	public AuthorizationCodeGenerationException() {
		super("Não foi possível gerar um código único tente novamente mais tarde");
	}	
}
