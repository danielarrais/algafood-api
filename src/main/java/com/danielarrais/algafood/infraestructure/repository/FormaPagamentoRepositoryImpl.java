package com.danielarrais.algafood.infraestructure.repository;

import com.danielarrais.algafood.domain.model.FormaPagamento;
import com.danielarrais.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<FormaPagamento> all() {
        return entityManager.createQuery("select c from FormaPagamento as c", FormaPagamento.class).getResultList();
    }

    @Transactional
    public FormaPagamento add(FormaPagamento formaPagamento) {
        return entityManager.merge(formaPagamento);
    }

    public FormaPagamento find(Long id) {
        return entityManager.find(FormaPagamento.class, id);
    }

    @Transactional
    public void remove(FormaPagamento formaPagamento) {
        formaPagamento = find(formaPagamento.getId());
        entityManager.remove(formaPagamento);
    }
}
