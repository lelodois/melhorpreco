package br.com.lelo.melhorpreco.business;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.lelo.melhorpreco.builder.PedidoBuilder;
import br.com.lelo.melhorpreco.common.MelhorPrecoTransaction;
import br.com.lelo.melhorpreco.external.PedidoDemandaAtendee;
import br.com.lelo.melhorpreco.external.PedidoDemandaItem;
import br.com.lelo.melhorpreco.model.Pedido;
import br.com.lelo.melhorpreco.model.ProdutoFornecedor;
import br.com.lelo.melhorpreco.repository.PedidoRepository;
import br.com.lelo.melhorpreco.service.PedidoService;
import br.com.lelo.melhorpreco.service.ProdutoFornecedorService;

@Service
public class PedidoBusiness implements PedidoDemandaAtendee, PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private ProdutoFornecedorService prodFornService;

	@Override
	public List<Pedido> findAll() {
		return repository.findAll(new Sort(Direction.DESC, "dataPedido"));
	}

	@Override
	@MelhorPrecoTransaction
	public Pedido save(Pedido pedido) {
		pedido.getItens().forEach(item -> {
			ProdutoFornecedor prodFornItem = item.getProdutoFornecedor();
			item.setProdutoFornecedor(prodFornService.findOne(prodFornItem.getId()));
		});
		return repository.save(pedido);
	}

	@Override
	public void atenderDemandas(List<PedidoDemandaItem> itens) {
		PedidoDemandaAtendee.super.atenderDemandas(itens);

		Map<String, List<PedidoDemandaItem>> cnpjDemandas = PedidoDemandaItem.groupByCnpj(itens);

		for (String cnpj : cnpjDemandas.keySet()) {
			PedidoBuilder pedido = PedidoBuilder.builder();
			cnpjDemandas.get(cnpj).forEach(demanda -> {
				ProdutoFornecedor prodForn = prodFornService.getBy(demanda.getFornecedorCnpj(),
						demanda.getProdutoGtin());
				pedido.withItem(prodForn, demanda.getQuantidade(), demanda.getPrecoFornecedor()).build();
			});
			this.save(pedido.build());
		}
	}
}
