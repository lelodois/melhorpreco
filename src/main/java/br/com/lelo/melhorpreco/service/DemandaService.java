package br.com.lelo.melhorpreco.service;

import java.util.List;

import br.com.lelo.melhorpreco.external.PedidoDemandaItem;

public interface DemandaService {

	void atenderDemandas(List<PedidoDemandaItem> demandas);

}