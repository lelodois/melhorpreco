package br.com.lelo.melhorpreco.service;

import java.util.List;

import br.com.lelo.melhorpreco.model.Pedido;

public interface PedidoService {

	List<Pedido> findAll();

	Pedido save(Pedido pedido);

}