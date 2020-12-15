package com.danielarrais.algafood.infraestructure.repository;

import com.danielarrais.algafood.domain.model.FormaPagamento;
import com.danielarrais.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        FormaPagamento formaPagamento = Optional
                .ofNullable(buscar(id))
                .orElseThrow(() -> {
                    // Esperava a existência de 1 cozinha
                    throw new EmptyResultDataAccessException(1);
                });

        entityManager.remove(formaPagamento);
    }
}
