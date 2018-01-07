package br.com.lelo.melhorpreco.service;

import java.util.Collection;

import br.com.lelo.melhorpreco.model.Produto;

public interface ProdutoService {

	void save(String gtin, String nome);

	Collection<Produto> findAll();

	void save(Produto produto);

}