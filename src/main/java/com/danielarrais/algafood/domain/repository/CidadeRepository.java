package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {
    List<Cidade> listar();
    Cidade buscar(Long id);
    void salvar(Cidade cidade);
    void remover(Cidade cidade);
}
