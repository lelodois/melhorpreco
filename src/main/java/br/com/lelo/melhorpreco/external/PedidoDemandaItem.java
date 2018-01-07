package br.com.lelo.melhorpreco.external;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import br.com.lelo.melhorpreco.model.Preco;

public class PedidoDemandaItem {

	private String produtoGtin;
	private String fornecedorCnpj;
	private Integer quantidade;
	private BigDecimal precoFornecedor;

	public PedidoDemandaItem() {
	}

	public PedidoDemandaItem(String gtin, Integer quantidade) {
		this.setProdutoGtin(gtin);
		this.setQuantidade(quantidade);
	}

	public void associarPreco(List<Preco> precos) {
		if (precos.isEmpty() == false) {
			Preco min = precos.stream().min((preco1, preco2) -> preco1.getValor().compareTo(preco2.getValor())).get();
			this.setFornecedorCnpj(min.getProdutoFornecedor().getCnpj());
			this.setPrecoFornecedor(min.getValor());
		}
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public BigDecimal getPrecoFornecedor() {
		return precoFornecedor;
	}

	public void setPrecoFornecedor(BigDecimal precoFornecedor) {
		this.precoFornecedor = precoFornecedor;
	}

	public String getProdutoGtin() {
		return produtoGtin;
	}

	public boolean isQuantidadeAtendida() {
		return precoFornecedor != null;
	}

	public void setFornecedorCnpj(String fornecedorCnpj) {
		this.fornecedorCnpj = fornecedorCnpj;
	}

	public void setProdutoGtin(String produtoGtin) {
		this.produtoGtin = produtoGtin;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getFornecedorCnpj() {
		return fornecedorCnpj;
	}

	public static Map<String, List<PedidoDemandaItem>> groupByCnpj(List<PedidoDemandaItem> itens) {
		Map<String, List<PedidoDemandaItem>> cnpjDemandas = Maps.newHashMap();
		itens.forEach(demanda -> {
			if (cnpjDemandas.containsKey(demanda.getFornecedorCnpj()) == false) {
				cnpjDemandas.put(demanda.getFornecedorCnpj(), new ArrayList<PedidoDemandaItem>());
			}
			cnpjDemandas.get(demanda.getFornecedorCnpj()).add(demanda);
		});
		return cnpjDemandas;
	}
}
