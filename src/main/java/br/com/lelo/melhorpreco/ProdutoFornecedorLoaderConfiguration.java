package br.com.lelo.melhorpreco;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import br.com.lelo.melhorpreco.model.Produto;
import br.com.lelo.melhorpreco.service.FornecedorService;
import br.com.lelo.melhorpreco.service.ProdutoService;

@Configuration
@DependsOn({ "produtoLoaderConfiguration", "fornecedorLoaderConfiguration" })
public class ProdutoFornecedorLoaderConfiguration {

	@Autowired
	private ProdutoService produtoBusiness;

	@Autowired
	private FornecedorService fornecedorBusiness;

	@PostConstruct
	public void loader() {
		Collection<Produto> produtos = produtoBusiness.findAll();
		this.addProdutosFornecedores(produtos);
		produtos.forEach(produto -> {
			produtoBusiness.save(produto);
		});
	}

	private void addProdutosFornecedores(Collection<Produto> produtos) {
		fornecedorBusiness.findAll().forEach(fornecedor -> {
			produtos.forEach(produto -> {
				produto.addFornecedor(fornecedor);
			});
		});
	}
}
