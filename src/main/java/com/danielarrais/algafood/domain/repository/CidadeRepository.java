package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Cidade;
import com.danielarrais.algafood.domain.repository.custom.BaseSimpleJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository  extends BaseSimpleJpaRepository<Cidade, Long> {
}
