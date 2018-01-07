package br.com.lelo.melhorpreco.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date dataPedido;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido", orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("pedido")
	private List<PedidoItem> itens;

	public Pedido() {
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public List<PedidoItem> getItens() {
		return itens;
	}

	public void setItens(List<PedidoItem> itens) {
		this.itens = itens;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void addItem(PedidoItem item) {
		if (this.itens == null) {
			this.itens = new ArrayList<PedidoItem>();
		}
		item.setPedido(this);
		this.itens.add(item);
		this.dataPedido = new Date();
	}
}
