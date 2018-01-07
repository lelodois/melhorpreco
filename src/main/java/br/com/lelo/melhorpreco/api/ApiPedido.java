package br.com.lelo.melhorpreco.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lelo.melhorpreco.external.PedidoDemandaItem;
import br.com.lelo.melhorpreco.model.Pedido;
import br.com.lelo.melhorpreco.service.DemandaService;
import br.com.lelo.melhorpreco.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class ApiPedido {

	@Autowired
	private PedidoService service;

	@Autowired
	private DemandaService demandaService;

	@GetMapping
	public List<Pedido> findAll() {
		return service.findAll();
	}

	@PostMapping(value = "novo")
	public void novo(@RequestBody List<PedidoDemandaItem> demandas) {
		demandaService.atenderDemandas(demandas);
	}

}
