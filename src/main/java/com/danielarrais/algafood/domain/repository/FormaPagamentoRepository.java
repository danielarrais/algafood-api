package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.FormaPagamento;
import com.danielarrais.algafood.domain.repository.custom.BaseSimpleJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends BaseSimpleJpaRepository<FormaPagamento, Long> {

}
