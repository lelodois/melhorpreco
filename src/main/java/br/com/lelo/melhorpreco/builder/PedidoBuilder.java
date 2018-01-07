package br.com.lelo.melhorpreco.builder;

import java.math.BigDecimal;

import com.google.common.collect.Lists;

import br.com.lelo.melhorpreco.common.ValidationUtils;
import br.com.lelo.melhorpreco.model.Pedido;
import br.com.lelo.melhorpreco.model.PedidoItem;
import br.com.lelo.melhorpreco.model.ProdutoFornecedor;

public class PedidoBuilder {

	private Pedido pedido = null;

	private PedidoBuilder() {
	}

	public static PedidoBuilder builder() {
		PedidoBuilder pedidoBuilder = new PedidoBuilder();
		pedidoBuilder.pedido = new Pedido();
		return pedidoBuilder;
	}

	public PedidoBuilder withItem(ProdutoFornecedor prodFornecedor, Integer quantidade, BigDecimal valor) {
		pedido.addItem(new PedidoItem(prodFornecedor, quantidade, valor));
		return this;
	}

	public Pedido build() {
		ValidationUtils.notEmpty(Lists.newArrayList("Itens Pedido"), pedido.getItens());
		return pedido;
	}
}
