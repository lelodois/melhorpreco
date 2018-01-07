package br.com.lelo.melhorpreco.common;

import java.util.List;

public class ItemObrigatorioException extends RuntimeException {

	private static final long serialVersionUID = -4824724824951274465L;

	private List<String> itens;

	public ItemObrigatorioException(List<String> itens) {
		this.itens = itens;
	}

	@Override
	public String getMessage() {
		return itens.toString();
	}
}
