package br.com.lelo.melhorpreco.external;

import java.util.List;

import com.google.common.collect.Lists;

import br.com.lelo.melhorpreco.common.ItemObrigatorioException;

public interface PedidoDemandaAtendee {

	public default void atenderDemandas(List<PedidoDemandaItem> itens) {
		this.validarDemanda(itens);
	}

	public default void validarDemanda(List<PedidoDemandaItem> itens) {
		if (itens == null || itens.isEmpty()) {
			throw new ItemObrigatorioException(Lists.newArrayList("itens"));
		}
	}
}
