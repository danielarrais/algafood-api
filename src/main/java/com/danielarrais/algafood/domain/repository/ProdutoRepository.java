package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Produto;
import com.danielarrais.algafood.domain.repository.custom.BaseSimpleJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProdutoRepository extends BaseSimpleJpaRepository<Produto, Long> {
    @Query("select p from Produto p where p.id = :produtoId and p.restaurante.id = :restauranteId")
    Optional<Produto> findById(Long restauranteId, long produtoId);
}

