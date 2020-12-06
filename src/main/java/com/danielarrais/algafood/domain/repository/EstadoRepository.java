package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> all();
    Estado find(Long id);
    Estado add(Estado estado);
    void remove(Estado estado);
}
