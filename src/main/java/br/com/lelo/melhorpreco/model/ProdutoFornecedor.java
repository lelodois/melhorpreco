package br.com.lelo.melhorpreco.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;

import br.com.lelo.melhorpreco.common.ValidationUtils;

@Entity
public class ProdutoFornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "produto")
	@JsonIgnoreProperties("fornecedores")
	private Produto produto;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "fornecedor")
	private Fornecedor fornecedor;

	@OneToMany(mappedBy = "produtoFornecedor", orphanRemoval = false, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("produtoFornecedor")
	private List<Preco> precos;

	@Enumerated(EnumType.STRING)
	private ProdutoFornecedorStatus status;

	public ProdutoFornecedor() {
	}

	public ProdutoFornecedor(Fornecedor fornecedor, Produto produto) {
		ValidationUtils.notEmpty(Lists.newArrayList("fornecedor", "produto"), fornecedor, produto);
		setProduto(produto);
		setFornecedor(fornecedor);
		setStatus(ProdutoFornecedorStatus.DISPONIVEL);
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public String getCnpj() {
		return fornecedor.getCnpj();
	}

	public String getGtin() {
		return produto.getGtin();
	}

	public ProdutoFornecedorStatus getStatus() {
		return status;
	}

	public void setStatus(ProdutoFornecedorStatus status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public List<Preco> getPrecos() {
		return precos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void adicionarPreco(Preco preco) {
		if (this.precos == null) {
			this.precos = Lists.newArrayList();
		}
		preco.setProdutoFornecedor(this);
		this.precos.add(preco);
	}
}
