package br.com.lelo.melhorpreco.builder;

import com.google.common.collect.Lists;

import br.com.lelo.melhorpreco.common.ValidationUtils;
import br.com.lelo.melhorpreco.model.Produto;

public class ProdutoBuilder {

	private Produto produto = null;

	private ProdutoBuilder() {
	}

	public static ProdutoBuilder builder() {
		return new ProdutoBuilder();
	}

	public ProdutoBuilder withGtinNome(String gtin, String nome) {
		this.produto = new Produto(gtin, nome);
		return this;
	}

	public Produto build() {
		ValidationUtils.notEmpty(Lists.newArrayList("produto"), produto);
		return produto;
	}
}
