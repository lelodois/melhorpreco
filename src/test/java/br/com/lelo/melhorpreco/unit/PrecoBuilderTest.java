package br.com.lelo.melhorpreco.unit;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.lelo.melhorpreco.builder.FornecedorBuilder;
import br.com.lelo.melhorpreco.builder.PrecoBuilder;
import br.com.lelo.melhorpreco.builder.ProdutoBuilder;
import br.com.lelo.melhorpreco.common.ItemObrigatorioException;
import br.com.lelo.melhorpreco.model.Fornecedor;
import br.com.lelo.melhorpreco.model.Produto;
import br.com.lelo.melhorpreco.model.ProdutoFornecedor;

public class PrecoBuilderTest {

	private ProdutoFornecedor produtoFornecedor;

	@Before
	public void before() {
		Produto produto = ProdutoBuilder.builder().withGtinNome("7894900011517", "REFRIGERANTE COCA-COLA 2LT").build();
		Fornecedor fornecedor = FornecedorBuilder.builder().withCnpjNome("56.918.868/0001-20", "Teste").build();
		this.produtoFornecedor = new ProdutoFornecedor(fornecedor, produto);
	}

	@Test(expected = ItemObrigatorioException.class)
	public void valorNaoInformadoTest() {
		PrecoBuilder.builder().withPreco(null, 1).withProdutoFornecedor(produtoFornecedor).build();
	}

	@Test(expected = ItemObrigatorioException.class)
	public void quantidadeMinimaNaoInformadoTest() {
		PrecoBuilder.builder().withPreco(new BigDecimal("1.5"), null).withProdutoFornecedor(produtoFornecedor).build();
	}

	public void produtoFornecedorNaoInformadoTest() {
		PrecoBuilder.builder().withPreco(new BigDecimal("1.5"), 1).build();
	}

	@Test
	public void umPrecoValidoTest() {
		Assert.assertEquals(PrecoBuilder.builder().withPreco(new BigDecimal("1.5"), 1)
				.withProdutoFornecedor(produtoFornecedor).build().size(), 1);
	}

	@Test
	public void doisPrecosValidosTest() {
		Assert.assertEquals(PrecoBuilder.builder().withPreco(new BigDecimal("1.4"), 2)
				.withPreco(new BigDecimal("1.5"), 1).withProdutoFornecedor(produtoFornecedor).build().size(), 2);
	}
}
