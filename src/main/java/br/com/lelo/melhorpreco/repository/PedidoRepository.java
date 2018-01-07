package br.com.lelo.melhorpreco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lelo.melhorpreco.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
