package br.com.lelo.melhorpreco.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.text.WordUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;

import br.com.lelo.melhorpreco.common.ValidationUtils;

@Entity
public class Produto {

	@Id
	@Length(min = 13, max = 13, message = "Gtin deve ter 13 caracteres")
	@NotEmpty
	private String gtin;

	@NotNull
	@NotEmpty
	@Length(max = 50)
	private String nome;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "produto", orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("produto")
	private List<ProdutoFornecedor> fornecedores;

	public Produto() {
	}

	public Produto(String gtin) {
		setGtin(gtin);
	}

	public Produto(String gtin, String nome) {
		this(gtin);
		ValidationUtils.notEmpty(Lists.newArrayList("gtin", "nome"), gtin, nome);
		setNome(WordUtils.capitalizeFully(nome));
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ProdutoFornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<ProdutoFornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public void addFornecedor(Fornecedor fornecedor) {
		if (this.fornecedores == null) {
			this.fornecedores = new ArrayList<ProdutoFornecedor>();
		}
		this.fornecedores.add(new ProdutoFornecedor(fornecedor, this));
	}
}
