package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {
    List<Permissao> all();
    Permissao find(Long id);
    Permissao add(Permissao permissao);
    void remove(Permissao permissao);
}
