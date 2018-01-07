package br.com.lelo.melhorpreco.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.lelo.melhorpreco.external.PedidoDemandaAtendee;
import br.com.lelo.melhorpreco.external.PedidoDemandaItem;
import br.com.lelo.melhorpreco.service.DemandaService;

@Service
public class DemandaBusiness implements DemandaService {

	@Autowired
	@Qualifier(value = "fornecedorBusiness")
	private PedidoDemandaAtendee fornecedorAtendee;

	@Autowired
	@Qualifier(value = "pedidoBusiness")
	private PedidoDemandaAtendee pedidoAtendee;

	@Autowired
	@Qualifier(value = "precoBusiness")
	private PedidoDemandaAtendee precoAtendee;

	@Autowired
	@Qualifier(value = "produtoBusiness")
	private PedidoDemandaAtendee produtoAtendee;

	@Override
	public void atenderDemandas(List<PedidoDemandaItem> demandas) {
		List<PedidoDemandaAtendee> pedidos = Lists.newArrayList();
		pedidos.add(produtoAtendee);
		pedidos.add(precoAtendee);
		pedidos.add(fornecedorAtendee);
		pedidos.add(pedidoAtendee);

		pedidos.forEach(atendee -> {
			atendee.atenderDemandas(demandas);
		});
	}

}
