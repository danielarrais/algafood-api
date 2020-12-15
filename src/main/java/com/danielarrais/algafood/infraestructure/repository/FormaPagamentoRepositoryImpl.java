package com.danielarrais.algafood.infraestructure.repository;

import com.danielarrais.algafood.domain.model.FormaPagamento;
import com.danielarrais.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<FormaPagamento> listar() {
        return entityManager.createQuery("select c from FormaPagamento as c", FormaPagamento.class).getResultList();
    }

    @Transactional
    public void salvar(FormaPagamento formaPagamento) {
        entityManager.merge(formaPagamento);
    }

    public FormaPagamento buscar(Long id) {
        return entityManager.find(FormaPagamento.class, id);
    }

    @Transactional
    public void remover(Long id) {
        var formaPagamento = buscar(id);
        entityManager.remove(formaPagamento);
    }
}
