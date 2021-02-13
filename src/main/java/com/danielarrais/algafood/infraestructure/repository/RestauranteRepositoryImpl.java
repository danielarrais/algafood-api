package com.danielarrais.algafood.infraestructure.repository;

import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.custom.RestauranteRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.danielarrais.algafood.infraestructure.spec.RestauranteSpecs.comFreteGratis;

@Repository
public class RestauranteRepositoryImpl extends SimpleJpaRepository<Restaurante, Long> implements RestauranteRepositoryCustom {

    @Autowired
    public RestauranteRepositoryImpl(EntityManager entityManager) {
        super(Restaurante.class, entityManager);
    }

    public List<Restaurante> findComFreteGratis() {
        return findAll(comFreteGratis());
    }
}
