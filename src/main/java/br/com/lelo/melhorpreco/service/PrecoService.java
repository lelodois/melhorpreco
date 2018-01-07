package br.com.lelo.melhorpreco.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.lelo.melhorpreco.model.Preco;

public interface PrecoService {

	void save(BigDecimal valor, Integer qtdeMinima, String gtin, String cnpj);

	List<Preco> findAll();

}