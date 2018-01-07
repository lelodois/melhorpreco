package br.com.lelo.melhorpreco;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.lelo.melhorpreco.service.ProdutoService;

@Configuration
public class ProdutoLoaderConfiguration {

	@Autowired
	private ProdutoService produtoBusiness;

	@PostConstruct
	public void loader() {
		produtoBusiness.save("7894900011517", "REFRIGERANTE COCA-COLA 2LT");
		produtoBusiness.save("7891910000197", "AÇÚCAR REFINADO UNIÃO 1KG");
		produtoBusiness.save("7892840222949", "SALGADINHO FANDANGOS QUEIJO");
		produtoBusiness.save("7891910007110", "AÇÚCAR DE CONFEITEIRO UNIÃO GLAÇÚCAR");
		produtoBusiness.save("7891000053508", "ACHOCOLATADO NESCAU 2.0");
		produtoBusiness.save("7891000100103", "LEITE CONDENSADO MOÇA");
		produtoBusiness.save("7891991010856", "CERVEJA BUDWEISER");
	}

}
