package br.com.alura.forum.config.validacao;

public class ErrorFormularioDto {
	
	private String campo;
	private String error;
	
	
	public ErrorFormularioDto(String campo, String error) {
		super();
		this.campo = campo;
		this.error = error;
	}


	public String getCampo() {
		return campo;
	}


	public String getError() {
		return error;
	}	

}
