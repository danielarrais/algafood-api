package com.danielarrais.algafood.domain.repository.custom;

import com.danielarrais.algafood.domain.model.Restaurante;

import java.util.List;
public interface RestauranteRepositoryCustom {
    List<Restaurante> findComFreteGratis();
}

