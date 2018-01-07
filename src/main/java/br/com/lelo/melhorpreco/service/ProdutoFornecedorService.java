package br.com.lelo.melhorpreco.service;

import java.util.Collection;
import java.util.List;

import br.com.lelo.melhorpreco.model.ProdutoFornecedor;

public interface ProdutoFornecedorService {

	ProdutoFornecedor getBy(String cnpj, String gtin);

	Collection<ProdutoFornecedor> findAll();

	ProdutoFornecedor findOne(Long id);

	List<ProdutoFornecedor> getFornecedoresByGtin(String gtin);

}