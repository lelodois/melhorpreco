package br.com.lelo.melhorpreco;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import br.com.lelo.melhorpreco.builder.PedidoBuilder;
import br.com.lelo.melhorpreco.model.Pedido;
import br.com.lelo.melhorpreco.model.ProdutoFornecedor;
import br.com.lelo.melhorpreco.service.PedidoService;
import br.com.lelo.melhorpreco.service.ProdutoFornecedorService;

@Configuration
@DependsOn({ "precoLoaderConfiguration" })
public class PedidoLoaderConfiguration {

	@Autowired
	private PedidoService pedidoBusiness;

	@Autowired
	private ProdutoFornecedorService produtoFornecedorBusiness;

	@PostConstruct
	public void loader() {
		ProdutoFornecedor prodFornecedor = produtoFornecedorBusiness.findAll().iterator().next();
		Pedido build = PedidoBuilder.builder().withItem(prodFornecedor, 100, BigDecimal.ONE).build();
		pedidoBusiness.save(build);
	}
}
