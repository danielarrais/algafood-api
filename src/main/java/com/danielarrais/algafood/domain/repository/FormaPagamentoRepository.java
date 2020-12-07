package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {
    List<FormaPagamento> listar();
    FormaPagamento buscar(Long id);
    void salvar(FormaPagamento formaPagamento);
    void remover(Long id);
}
