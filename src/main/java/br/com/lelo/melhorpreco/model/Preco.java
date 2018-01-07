package br.com.lelo.melhorpreco.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.google.common.collect.Lists;

import br.com.lelo.melhorpreco.common.ValidationUtils;

@Entity
public class Preco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Min(value = 1)
	@NotNull
	private Integer quantidadeMinima;

	@NotNull
	private BigDecimal valor;

	private Date dataAtualizacao;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "produtoFornecedor")
	private ProdutoFornecedor produtoFornecedor;

	public Preco() {

	}

	public Preco(BigDecimal valor, Integer quantidadeMinima) {
		ValidationUtils.notEmpty(Lists.newArrayList("valor", "quantidadeMinima"), valor, quantidadeMinima);
		setValor(valor);
		setQuantidadeMinima(quantidadeMinima);
		setDataAtualizacao(new Date());
	}

	public Long getId() {
		return id;
	}

	public Integer getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setQuantidadeMinima(Integer quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public ProdutoFornecedor getProdutoFornecedor() {
		return produtoFornecedor;
	}

	public void setProdutoFornecedor(ProdutoFornecedor produtoFornecedor) {
		this.produtoFornecedor = produtoFornecedor;
	}
}
