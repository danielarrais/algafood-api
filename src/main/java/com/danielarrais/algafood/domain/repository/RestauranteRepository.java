package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.custom.RestauranteRepositoryCustom;
import com.danielarrais.algafood.domain.repository.custom.CustomSimpleJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends CustomSimpleJpaRepository<Restaurante, Long>, JpaSpecificationExecutor<Restaurante>, RestauranteRepositoryCustom {
}
