package br.com.lelo.melhorpreco.service;

import java.util.Collection;

import br.com.lelo.melhorpreco.model.Fornecedor;

public interface FornecedorService {

	void save(String cnpj, String nome);

	Collection<Fornecedor> findAll();

}