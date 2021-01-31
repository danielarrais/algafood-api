package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.custom.BaseSimpleJpaRepository;
import com.danielarrais.algafood.domain.repository.custom.RestauranteRepositoryCustomQueries;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RestauranteRepository extends BaseSimpleJpaRepository<Restaurante, Long>, RestauranteRepositoryCustomQueries {
    @Query("from Restaurante r join fetch r.cozinha join fetch r.formasPagamento")
    List<Restaurante> findAll();
}
