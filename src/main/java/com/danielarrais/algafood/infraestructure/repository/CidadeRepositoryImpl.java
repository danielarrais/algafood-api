package com.danielarrais.algafood.infraestructure.repository;

import com.danielarrais.algafood.domain.model.Cidade;
import com.danielarrais.algafood.domain.repository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cidade> listar() {
        return entityManager.createQuery("select c from Cidade as c", Cidade.class).getResultList();
    }

    @Transactional
    public void salvar(Cidade cidade) {
        entityManager.merge(cidade);
    }

    public Cidade buscar(Long id) {
        return entityManager.find(Cidade.class, id);
    }

    @Transactional
    public void remover(Long id) {
        Cidade cidade = Optional
                .ofNullable(buscar(id))
                .orElseThrow(() -> {
                    // Esperava a existência de 1 cozinha
                    throw new EmptyResultDataAccessException(1);
                });

        entityManager.remove(cidade);
    }
}
