package br.com.lelo.melhorpreco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lelo.melhorpreco.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
}
