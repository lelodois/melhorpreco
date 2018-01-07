package br.com.lelo.melhorpreco.unit;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import br.com.lelo.melhorpreco.builder.PedidoBuilder;
import br.com.lelo.melhorpreco.builder.ProdutoBuilder;
import br.com.lelo.melhorpreco.common.ItemObrigatorioException;
import br.com.lelo.melhorpreco.model.Pedido;
import br.com.lelo.melhorpreco.model.ProdutoFornecedor;

public class PedidoBuilderTest {

	@Test(expected = ItemObrigatorioException.class)
	public void quantidadeNaoInformadoTest() {
		PedidoBuilder.builder().withItem(new ProdutoFornecedor(), null, BigDecimal.TEN).build();
	}

	@Test(expected = ItemObrigatorioException.class)
	public void precoNaoInformadoTest() {
		PedidoBuilder.builder().withItem(new ProdutoFornecedor(), 10, null).build();
	}

	@Test(expected = ItemObrigatorioException.class)
	public void produtoFornecedorNaoInformadoTest() {
		PedidoBuilder.builder().withItem(null, 10, BigDecimal.TEN).build();
	}

	@Test(expected = ItemObrigatorioException.class)
	public void pedidoSemItensTest() {
		ProdutoBuilder.builder().build();
	}

	@Test
	public void itemValidoTest() {
		Pedido build = PedidoBuilder.builder().withItem(new ProdutoFornecedor(), 10, BigDecimal.TEN).build();
		Assert.assertNotNull(build);
	}

}
