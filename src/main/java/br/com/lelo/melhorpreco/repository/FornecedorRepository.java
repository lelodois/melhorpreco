package br.com.lelo.melhorpreco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lelo.melhorpreco.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, String> {

}
