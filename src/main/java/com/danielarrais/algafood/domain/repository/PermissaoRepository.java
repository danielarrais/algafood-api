package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Permissao;
import com.danielarrais.algafood.domain.repository.custom.BaseSimpleJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends BaseSimpleJpaRepository<Permissao, Long> {
}
