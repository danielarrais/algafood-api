package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> listar();
    Estado buscar(Long id);
    void salvar(Estado estado);
    void remover(Long id);
}
