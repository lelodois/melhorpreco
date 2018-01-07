package br.com.lelo.melhorpreco;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import br.com.lelo.melhorpreco.service.FornecedorService;

@Configuration
public class FornecedorLoaderConfiguration {

	@Autowired
	@Qualifier(value = "fornecedorBusiness")
	private FornecedorService fornecedorBusiness;

	@PostConstruct
	public void init() {
		fornecedorBusiness.save("56918868000120", "Mondelez");
		fornecedorBusiness.save("74546283000108", "Brasal Refrigerantes");
		fornecedorBusiness.save("64756369000101", "Açucar União");
		fornecedorBusiness.save("88739747000107", "Nestle");
	}

}
