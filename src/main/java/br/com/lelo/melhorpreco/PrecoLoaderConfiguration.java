package br.com.lelo.melhorpreco;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import br.com.lelo.melhorpreco.service.PrecoService;
import br.com.lelo.melhorpreco.service.ProdutoFornecedorService;

@Configuration
@DependsOn({ "produtoFornecedorLoaderConfiguration" })
public class PrecoLoaderConfiguration {

	@Autowired
	private PrecoService precoBusiness;

	@Autowired
	private ProdutoFornecedorService produtoFornecedorBusiness;

	@PostConstruct
	public void loader() {
		produtoFornecedorBusiness.findAll().forEach(item -> {
			Integer qtdeMinima = RandomUtils.nextInt(1, 50);
			BigDecimal valor = BigDecimal.valueOf(RandomUtils.nextDouble(0, 50));
			precoBusiness.save(valor, qtdeMinima, item.getGtin(), item.getCnpj());
		});
	}
}
