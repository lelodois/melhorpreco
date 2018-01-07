package br.com.lelo.melhorpreco.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.collect.Lists;

import br.com.lelo.melhorpreco.common.ValidationUtils;

@Entity
@Table
public class PedidoItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "pedido")
	private Pedido pedido;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "produtoFornecedor")
	private ProdutoFornecedor produtoFornecedor;

	private Integer quantidadeSolicitada;

	private BigDecimal valor;

	public PedidoItem() {
	}

	public PedidoItem(ProdutoFornecedor prodForn, Integer qtde, BigDecimal valor) {
		ValidationUtils.notEmpty(Lists.newArrayList("prodForn", "quantidade", "valor"), prodForn, qtde, valor);
		setProdutoFornecedor(prodForn);
		setQuantidadeSolicitada(qtde);
		setValor(valor);
	}

	public Long getId() {
		return id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public ProdutoFornecedor getProdutoFornecedor() {
		return produtoFornecedor;
	}

	public Integer getQuantidadeSolicitada() {
		return quantidadeSolicitada;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public void setProdutoFornecedor(ProdutoFornecedor produtoFornecedor) {
		this.produtoFornecedor = produtoFornecedor;
	}

	public void setQuantidadeSolicitada(Integer quantidadeSolicitada) {
		this.quantidadeSolicitada = quantidadeSolicitada;
	}
}
