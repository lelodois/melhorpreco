package br.com.lelo.melhorpreco.business;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.lelo.melhorpreco.builder.ProdutoBuilder;
import br.com.lelo.melhorpreco.common.MelhorPrecoTransaction;
import br.com.lelo.melhorpreco.external.PedidoDemandaAtendee;
import br.com.lelo.melhorpreco.external.PedidoDemandaItem;
import br.com.lelo.melhorpreco.model.Produto;
import br.com.lelo.melhorpreco.repository.ProdutoRepository;
import br.com.lelo.melhorpreco.service.ProdutoService;

@Service
public class ProdutoBusiness implements ProdutoService, PedidoDemandaAtendee {

	@Autowired
	private ProdutoRepository repository;

	@Override
	@MelhorPrecoTransaction
	public void save(String gtin, String nome) {
		Produto produto = repository.findOne(Example.of(new Produto(gtin)));
		if (produto == null) {
			this.save(ProdutoBuilder.builder().withGtinNome(gtin, nome).build());
		}
	}

	@Override
	public Collection<Produto> findAll() {
		List<Produto> produtos = repository.findAll();
		produtos.sort(new Comparator<Produto>() {
			public int compare(Produto prod, Produto other) {
				return prod.getNome().compareTo(other.getNome());
			};
		});
		return produtos;
	}

	@Override
	public void atenderDemandas(List<PedidoDemandaItem> itens) {
		PedidoDemandaAtendee.super.atenderDemandas(itens);
		for (Iterator<PedidoDemandaItem> iterator = itens.iterator(); iterator.hasNext();) {
			PedidoDemandaItem demanda = iterator.next();
			if (demanda.getQuantidade() == null || demanda.getQuantidade() <= 0) {
				iterator.remove();
			}
		}
	}

	@Override
	public void save(Produto produto) {
		repository.save(produto);
	}

}
