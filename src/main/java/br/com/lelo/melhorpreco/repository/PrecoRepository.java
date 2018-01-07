package br.com.lelo.melhorpreco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.lelo.melhorpreco.model.Preco;
import br.com.lelo.melhorpreco.model.ProdutoFornecedorStatus;

public interface PrecoRepository extends JpaRepository<Preco, Long> {

	@Query(value = "select p from Preco p " 
				 + " where p.quantidadeMinima <= :qtdeMinima "
				 + "   and p.produtoFornecedor.produto.gtin = :gtin " 
				 + "   and p.produtoFornecedor.status = :status")
	List<Preco> findPrecosAtendido( @Param("gtin") String produtoGtin, 
									@Param("qtdeMinima") Integer quantidade, 
									@Param("status") ProdutoFornecedorStatus status);

}
