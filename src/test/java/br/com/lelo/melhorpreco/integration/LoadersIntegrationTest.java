package br.com.lelo.melhorpreco.integration;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.lelo.melhorpreco.MelhorPrecoApplication;
import br.com.lelo.melhorpreco.service.FornecedorService;
import br.com.lelo.melhorpreco.service.PedidoService;
import br.com.lelo.melhorpreco.service.PrecoService;
import br.com.lelo.melhorpreco.service.ProdutoFornecedorService;
import br.com.lelo.melhorpreco.service.ProdutoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MelhorPrecoApplication.class })
@WebAppConfiguration
@Transactional
@EnableTransactionManagement
@Rollback(true)
public class LoadersIntegrationTest {

	@Autowired
	private ProdutoService produtoBusiness;

	@Autowired
	private PedidoService pedidoBusiness;

	@Autowired
	@Qualifier(value = "fornecedorBusiness")
	private FornecedorService fornecedorBusiness;

	@Autowired
	private ProdutoFornecedorService produtoFornecedorBusiness;

	@Autowired
	private PrecoService precoBusiness;

	@Test
	public void produtosTest() {
		Assert.assertEquals(7, produtoBusiness.findAll().size());
	}

	@Test
	public void fornecedorTest() {
		Assert.assertEquals(4, fornecedorBusiness.findAll().size());
	}

	@Test
	public void produtofornecedorTest() {
		Assert.assertEquals(7 * 4, produtoFornecedorBusiness.findAll().size());
	}

	@Test
	public void precosTest() {
		Assert.assertEquals(7 * 4, precoBusiness.findAll().size());
	}

	@Test
	public void pedidoTest() {
		Assert.assertEquals(1, pedidoBusiness.findAll().size());
	}

}