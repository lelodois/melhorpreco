package br.com.lelo.melhorpreco.unit;

import org.junit.Test;

import br.com.lelo.melhorpreco.builder.ProdutoBuilder;
import br.com.lelo.melhorpreco.common.ItemObrigatorioException;

public class ProdutoBuilderTest {

	@Test(expected = ItemObrigatorioException.class)
	public void nomeNaoInformadoTest() {
		ProdutoBuilder.builder().withGtinNome("7891910000197", null).build();
	}

	@Test(expected = ItemObrigatorioException.class)
	public void nomeVazioTest() {
		ProdutoBuilder.builder().withGtinNome("7891910000197", "").build();
	}

	@Test(expected = ItemObrigatorioException.class)
	public void gtinNaoInformadoTest() {
		ProdutoBuilder.builder().withGtinNome(null, "Teste").build();
	}

	@Test(expected = ItemObrigatorioException.class)
	public void gtinVazioTest() {
		ProdutoBuilder.builder().withGtinNome("", "Teste").build();
	}

	@Test
	public void itemValidoTest() {
		ProdutoBuilder.builder().withGtinNome("7891910000197", "Teste").build();
	}

}
