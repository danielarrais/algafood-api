package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.FotoProduto;
import com.danielarrais.algafood.domain.model.Produto;
import com.danielarrais.algafood.domain.repository.custom.BaseSimpleJpaRepository;
import com.danielarrais.algafood.domain.repository.custom.ProdutoRepositoryCustom;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProdutoRepository extends BaseSimpleJpaRepository<Produto, Long>, ProdutoRepositoryCustom {
    @Query("select p from Produto p where p.id = :produtoId and p.restaurante.id = :restauranteId")
    Optional<Produto> findById(Long restauranteId, long produtoId);

    FotoProduto saveAndFlush(FotoProduto fotoProduto);

    @Query("select f from FotoProduto f join f.produto p "
            + "where p.restaurante.id = :restauranteId and f.produto.id = :produtoId")
    Optional<FotoProduto> findFotoById(Long restauranteId, Long produtoId);
}

