package br.com.lelo.melhorpreco.business;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.lelo.melhorpreco.common.ValidationUtils;
import br.com.lelo.melhorpreco.model.Fornecedor;
import br.com.lelo.melhorpreco.model.Produto;
import br.com.lelo.melhorpreco.model.ProdutoFornecedor;
import br.com.lelo.melhorpreco.model.ProdutoFornecedorStatus;
import br.com.lelo.melhorpreco.repository.ProdutoFornecedorRepository;
import br.com.lelo.melhorpreco.service.ProdutoFornecedorService;

@Service
public class ProdutoFornecedorBusiness implements ProdutoFornecedorService {

	@Autowired
	private ProdutoFornecedorRepository repository;

	@Override
	public ProdutoFornecedor getBy(String cnpj, String gtin) {
		ProdutoFornecedor example = new ProdutoFornecedor(new Fornecedor(cnpj), new Produto(gtin));
		ProdutoFornecedor produtoFornecedor = repository.findOne(Example.of(example));
		ValidationUtils.notEmpty(Lists.newArrayList("produtoFornecedor"), produtoFornecedor);
		return produtoFornecedor;
	}

	@Override
	public Collection<ProdutoFornecedor> findAll() {
		return repository.findAll();
	}

	@Override
	public ProdutoFornecedor findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<ProdutoFornecedor> getFornecedoresByGtin(String gtin) {
		ProdutoFornecedor example = new ProdutoFornecedor(new Fornecedor(null), new Produto(gtin));
		example.setStatus(ProdutoFornecedorStatus.DISPONIVEL);
		return repository.findAll(Example.of(example));
	}

}
