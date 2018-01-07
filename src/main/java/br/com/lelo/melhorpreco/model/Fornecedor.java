package br.com.lelo.melhorpreco.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.collect.Lists;

import br.com.caelum.stella.format.CNPJFormatter;
import br.com.lelo.melhorpreco.common.ValidationUtils;

@Entity
public class Fornecedor {

	@Id
	private String cnpj;

	@NotNull
	@NotEmpty
	@Length(max = 50)
	private String nome;

	public Fornecedor() {
	}

	public Fornecedor(String cnpj) {
		setCnpj(cnpj);
	}

	public Fornecedor(String cnpj, String nome) {
		this(cnpj);
		ValidationUtils.notEmpty(Lists.newArrayList("cnpj", "nome"), cnpj, nome);
		setNome(nome);
		setCnpj(this.unformatCpnj());
	}

	public String unformatCpnj() {
		return new CNPJFormatter().unformat(cnpj);
	}

	public String formatCpnj() {
		return new CNPJFormatter().format(cnpj);
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
