package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {
    List<Permissao> listar();
    Permissao buscar(Long id);
    void salvar(Permissao permissao);
    void remover(Long id);
}
