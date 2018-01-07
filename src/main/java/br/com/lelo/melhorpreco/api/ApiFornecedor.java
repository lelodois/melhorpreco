package br.com.lelo.melhorpreco.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lelo.melhorpreco.model.Fornecedor;
import br.com.lelo.melhorpreco.service.FornecedorService;

@RestController
@RequestMapping("/api/fornecedor")
public class ApiFornecedor {

	@Autowired
	private FornecedorService service;

	@GetMapping
	public Collection<Fornecedor> findAll() {
		return service.findAll();
	}
}
