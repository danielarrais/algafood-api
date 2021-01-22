package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Produto;
import com.danielarrais.algafood.domain.repository.custom.BaseSimpleJpaRepository;

public interface ProdutoRepository extends BaseSimpleJpaRepository<Produto, Long> {
}

