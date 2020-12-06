package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {
    List<FormaPagamento> all();
    FormaPagamento find(Long id);
    FormaPagamento add(FormaPagamento formaPagamento);
    void remove(FormaPagamento formaPagamento);
}
