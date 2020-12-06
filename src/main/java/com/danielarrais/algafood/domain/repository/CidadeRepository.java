package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {
    List<Cidade> all();
    Cidade find(Long id);
    Cidade add(Cidade cidade);
    void remove(Cidade cidade);
}
