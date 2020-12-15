package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    List<Restaurante> listar();
    Restaurante buscar(Long id);
    void salvar(Restaurante Restaurante);
    void remover(Long id);
}
