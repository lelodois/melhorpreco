package br.com.lelo.melhorpreco.business;

import static br.com.lelo.melhorpreco.model.ProdutoFornecedorStatus.DISPONIVEL;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.lelo.melhorpreco.builder.PrecoBuilder;
import br.com.lelo.melhorpreco.common.MelhorPrecoTransaction;
import br.com.lelo.melhorpreco.common.ValidationUtils;
import br.com.lelo.melhorpreco.external.PedidoDemandaAtendee;
import br.com.lelo.melhorpreco.external.PedidoDemandaItem;
import br.com.lelo.melhorpreco.model.Preco;
import br.com.lelo.melhorpreco.model.ProdutoFornecedor;
import br.com.lelo.melhorpreco.repository.PrecoRepository;
import br.com.lelo.melhorpreco.service.PrecoService;
import br.com.lelo.melhorpreco.service.ProdutoFornecedorService;

@Service
public class PrecoBusiness implements PrecoService, PedidoDemandaAtendee {

	@Autowired
	private ProdutoFornecedorService prodFornService;

	@Autowired
	private PrecoRepository repository;

	@Override
	@MelhorPrecoTransaction
	public void save(BigDecimal valor, Integer qtdeMinima, String gtin, String cnpj) {
		ProdutoFornecedor produtoFornecedor = prodFornService.getBy(cnpj, gtin);

		PrecoBuilder.builderBy(valor, qtdeMinima, produtoFornecedor).forEach(preco -> {
			repository.save(preco);
		});
	}

	@Override
	public List<Preco> findAll() {
		return repository.findAll();
	}

	@Override
	public void atenderDemandas(List<PedidoDemandaItem> itens) {
		PedidoDemandaAtendee.super.atenderDemandas(itens);
		itens.forEach(item -> {
			item.associarPreco(repository.findPrecosAtendido(item.getProdutoGtin(), item.getQuantidade(), DISPONIVEL));
		});

	}

	@Override
	public void validarDemanda(List<PedidoDemandaItem> itens) {
		PedidoDemandaAtendee.super.validarDemanda(itens);
		itens.forEach(item -> {
			ValidationUtils.notEmpty(Lists.newArrayList("Produto"), item.getProdutoGtin());
		});
	}

}
