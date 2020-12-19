package com.danielarrais.algafood.infraestructure.repository;

import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.RestauranteRepository;
import com.danielarrais.algafood.domain.repository.custom.RestauranteRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.danielarrais.algafood.infraestructure.spec.RestauranteSpecs.comFreteGratis;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustom {
    @Lazy
    @Autowired
    private RestauranteRepository repository;

    public List<Restaurante> findComFreteGratis() {
        return repository.findAll(comFreteGratis());
    }
}
