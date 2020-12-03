package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    List<Restaurante> all();
    Restaurante find(Long id);
    Restaurante add(Restaurante Restaurante);
    void remove(Restaurante Restaurante);
}
