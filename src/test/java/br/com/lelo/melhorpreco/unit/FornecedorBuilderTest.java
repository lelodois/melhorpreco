package br.com.lelo.melhorpreco.unit;

import org.junit.Assert;
import org.junit.Test;

import br.com.lelo.melhorpreco.builder.FornecedorBuilder;
import br.com.lelo.melhorpreco.common.ItemObrigatorioException;

public class FornecedorBuilderTest {

	@Test(expected = ItemObrigatorioException.class)
	public void nomeNaoInformadoTest() {
		FornecedorBuilder.builder().withCnpjNome("56.918.868/0001-20", null).build();
	}

	@Test(expected = ItemObrigatorioException.class)
	public void nomeVazioTest() {
		FornecedorBuilder.builder().withCnpjNome("56.918.868/0001-20", "").build();
	}

	@Test(expected = ItemObrigatorioException.class)
	public void cnpjNaoInformadoTest() {
		FornecedorBuilder.builder().withCnpjNome(null, "Teste").build();
	}

	@Test(expected = ItemObrigatorioException.class)
	public void cnpjVazioTest() {
		FornecedorBuilder.builder().withCnpjNome("", "Teste").build();
	}

	@Test
	public void cnpjFormatadoTest() {
		Assert.assertEquals(FornecedorBuilder.builder().withCnpjNome("56918868000120", "Teste").build().formatCpnj(),
				"56.918.868/0001-20");
	}

	@Test
	public void cnpjNaoFormatadoTest() {
		Assert.assertEquals(FornecedorBuilder.builder().withCnpjNome("56918868000120", "Teste").build().unformatCpnj(),
				"56918868000120");
	}

	@Test
	public void cnpjDesformatarTest() {
		Assert.assertEquals(FornecedorBuilder.builder().withCnpjNome("56.918.868/0001-20", "Teste").build().getCnpj(),
				"56918868000120");
	}
}
