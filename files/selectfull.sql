	select 
		prod.nome					produto,
		f.nome						fornecedor,
		pf.status					status, 
		pre.valor					valorItem, 
		p.id 						pedido, 
		p.datapedido 				pedidoData,
		pre.quantidademinima 		qtdeMinima, 
		pi.quantidadesolicitada 	qtdeSolicitada
	from pedido p
	inner join pedidoitem pi
			on p.id = pi.pedido
	inner join produtofornecedor pf
			on pf.id = pi.produtofornecedor
	inner join preco pre
			on pre.produtofornecedor = pf.id
	inner join produto prod
			on prod.gtin = pf.produto
	inner join fornecedor f
			on f.cnpj = pf.fornecedor;