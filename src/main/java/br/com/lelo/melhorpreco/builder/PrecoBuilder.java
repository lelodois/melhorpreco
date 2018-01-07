package br.com.lelo.melhorpreco.builder;

import java.math.BigDecimal;
import java.util.List;

import com.google.common.collect.Lists;

import br.com.lelo.melhorpreco.common.ValidationUtils;
import br.com.lelo.melhorpreco.model.Preco;
import br.com.lelo.melhorpreco.model.ProdutoFornecedor;

public class PrecoBuilder {

	private List<Preco> precos = Lists.newArrayList();
	private ProdutoFornecedor produtoFornecedor = null;

	private PrecoBuilder() {
	}

	public static PrecoBuilder builder() {
		return new PrecoBuilder();
	}

	public PrecoBuilder withPreco(BigDecimal valor, Integer quantidadeMinima) {
		precos.add(new Preco(valor, quantidadeMinima));
		return this;
	}

	public PrecoBuilder withProdutoFornecedor(ProdutoFornecedor produtoFornecedor) {
		this.produtoFornecedor = produtoFornecedor;
		return this;
	}

	public List<Preco> build() {
		ValidationUtils.notEmpty(Lists.newArrayList("precos", "produtoFornecedor"), precos, produtoFornecedor);
		precos.forEach(preco -> {
			produtoFornecedor.adicionarPreco(preco);
		});
		return precos;
	}

	public static List<Preco> builderBy(BigDecimal valor, Integer qtde, ProdutoFornecedor prodFornecedor) {
		return builder().withProdutoFornecedor(prodFornecedor).withPreco(valor, qtde).build();
	}
}
