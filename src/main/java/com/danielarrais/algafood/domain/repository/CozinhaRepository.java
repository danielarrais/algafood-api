package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Cozinha;
import java.util.List;

public interface CozinhaRepository {
    List<Cozinha> all();
    Cozinha find(Long id);
    Cozinha add(Cozinha cozinha);
    void remove(Cozinha cozinha);
}
