package br.com.lelo.melhorpreco.integration;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.collect.Lists;

import br.com.lelo.melhorpreco.MelhorPrecoApplication;
import br.com.lelo.melhorpreco.external.PedidoDemandaItem;
import br.com.lelo.melhorpreco.service.DemandaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MelhorPrecoApplication.class })
@WebAppConfiguration
@Transactional
@EnableTransactionManagement
@Rollback(true)
public class DemandaBusinessTest {

	@Autowired
	private DemandaService demandaService;

	@Test
	public void gerarUmPedidoTest() {
		List<PedidoDemandaItem> itens = Lists.newArrayList();
		itens.add(new PedidoDemandaItem("7891910000197", 5));
		demandaService.atenderDemandas(itens);
	}
}
