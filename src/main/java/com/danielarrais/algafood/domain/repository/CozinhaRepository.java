package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Cozinha;
import com.danielarrais.algafood.domain.repository.custom.BaseSimpleJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository  extends BaseSimpleJpaRepository<Cozinha, Long> {
}
