package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Estado;
import com.danielarrais.algafood.domain.repository.custom.BaseSimpleJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends BaseSimpleJpaRepository<Estado, Long> {
}
