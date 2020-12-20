package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.custom.BaseSimpleJpaRepository;
import com.danielarrais.algafood.domain.repository.custom.RestauranteRepositoryCustomQueries;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends BaseSimpleJpaRepository<Restaurante, Long>, RestauranteRepositoryCustomQueries {
}
