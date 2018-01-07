package br.com.lelo.melhorpreco.unit;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import br.com.lelo.melhorpreco.external.PedidoDemandaItem;
import br.com.lelo.melhorpreco.model.Fornecedor;
import br.com.lelo.melhorpreco.model.Preco;
import br.com.lelo.melhorpreco.model.Produto;
import br.com.lelo.melhorpreco.model.ProdutoFornecedor;

public class PedidoDemandaItemTest {

	@Test
	public void quantidadeNaoAtendidaTest() {
		PedidoDemandaItem item = new PedidoDemandaItem("7891910000197", 10);
		item.associarPreco(new ArrayList<Preco>());
		Assert.assertFalse(item.isQuantidadeAtendida());
	}

	@Test
	public void quantidadeAtendidaPrecoUnicoTest() {
		PedidoDemandaItem item = new PedidoDemandaItem("7891910000197", 10);
		ProdutoFornecedor prodForn = new ProdutoFornecedor(new Fornecedor("56918868000120"), new Produto());
		Preco preco = new Preco(BigDecimal.TEN, 1);
		preco.setProdutoFornecedor(prodForn);
		item.associarPreco(Lists.newArrayList(preco));
		Assert.assertTrue(item.isQuantidadeAtendida());
		Assert.assertEquals(item.getFornecedorCnpj(), prodForn.getCnpj());
	}

	@Test
	public void quantidadeAtendidaMenorPrecoTest() {
		PedidoDemandaItem item = new PedidoDemandaItem("7891910000197", 10);
		ProdutoFornecedor prodForn1 = new ProdutoFornecedor(new Fornecedor("56918868000120"), new Produto());
		Preco preco1 = new Preco(BigDecimal.TEN, 1);
		preco1.setProdutoFornecedor(prodForn1);

		ProdutoFornecedor prodForn2 = new ProdutoFornecedor(new Fornecedor("56918868000220"), new Produto());
		Preco preco2 = new Preco(BigDecimal.TEN.min(BigDecimal.ONE), 1);
		preco2.setProdutoFornecedor(prodForn2);

		item.associarPreco(Lists.newArrayList(preco1, preco2));
		Assert.assertTrue(item.isQuantidadeAtendida());
		Assert.assertEquals(item.getFornecedorCnpj(), prodForn2.getCnpj());
	}
}
