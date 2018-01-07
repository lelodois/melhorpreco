package br.com.lelo.melhorpreco.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lelo.melhorpreco.model.Produto;
import br.com.lelo.melhorpreco.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ApiProduto {

	@Autowired
	private ProdutoService service;

	@GetMapping
	public Collection<Produto> findAll() {
		return service.findAll();
	}
}
