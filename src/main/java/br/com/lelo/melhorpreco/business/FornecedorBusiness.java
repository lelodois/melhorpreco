package br.com.lelo.melhorpreco.business;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.lelo.melhorpreco.builder.FornecedorBuilder;
import br.com.lelo.melhorpreco.common.MelhorPrecoTransaction;
import br.com.lelo.melhorpreco.common.ValidationUtils;
import br.com.lelo.melhorpreco.external.PedidoDemandaAtendee;
import br.com.lelo.melhorpreco.external.PedidoDemandaItem;
import br.com.lelo.melhorpreco.model.Fornecedor;
import br.com.lelo.melhorpreco.repository.FornecedorRepository;
import br.com.lelo.melhorpreco.service.FornecedorService;

@Service
public class FornecedorBusiness implements PedidoDemandaAtendee, FornecedorService {

	@Autowired
	private FornecedorRepository repository;

	@Override
	@MelhorPrecoTransaction
	public void save(String cnpj, String nome) {
		Fornecedor fornecedor = repository.findOne(Example.of(new Fornecedor(cnpj)));
		if (fornecedor == null) {
			repository.save(FornecedorBuilder.builder().withCnpjNome(cnpj, nome).build());
		}
	}

	@Override
	public Collection<Fornecedor> findAll() {
		return repository.findAll();
	}

	@Override
	public void validarDemanda(List<PedidoDemandaItem> itens) {
		PedidoDemandaAtendee.super.validarDemanda(itens);
		String errorMsg = "Sem Fornecedor para a quantidade informada";
		itens.forEach(item -> {
			ValidationUtils.notEmpty(Lists.newArrayList(errorMsg), item.getFornecedorCnpj());
		});
	}
}
