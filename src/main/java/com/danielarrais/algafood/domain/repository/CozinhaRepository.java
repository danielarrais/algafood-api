package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Cozinha;
import java.util.List;

public interface CozinhaRepository {
    List<Cozinha> listar();
    Cozinha buscar(Long id);
    Cozinha adicionar(Cozinha cozinha);
    void remover(Cozinha cozinha);
}
