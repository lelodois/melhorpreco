package br.com.lelo.melhorpreco.builder;

import com.google.common.collect.Lists;

import br.com.lelo.melhorpreco.common.ValidationUtils;
import br.com.lelo.melhorpreco.model.Fornecedor;

public class FornecedorBuilder {

	private Fornecedor fornecedor = null;

	private FornecedorBuilder() {
	}

	public static FornecedorBuilder builder() {
		return new FornecedorBuilder();
	}

	public FornecedorBuilder withCnpjNome(String cnpj, String nome) {
		this.fornecedor = new Fornecedor(cnpj, nome);
		return this;
	}

	public Fornecedor build() {
		ValidationUtils.notEmpty(Lists.newArrayList("fornecedor"), fornecedor);
		return fornecedor;
	}
}
