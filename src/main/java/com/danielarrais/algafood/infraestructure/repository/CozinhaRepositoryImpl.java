package com.danielarrais.algafood.infraestructure.repository;

import com.danielarrais.algafood.domain.model.Cozinha;
import com.danielarrais.algafood.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cozinha> all() {
        return entityManager.createQuery("select c from Cozinha as c", Cozinha.class).getResultList();
    }

    @Transactional
    public Cozinha add(Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }

    public Cozinha find(Long id) {
        return entityManager.find(Cozinha.class, id);
    }

    @Transactional
    public void remove(Cozinha cozinha) {
        cozinha = find(cozinha.getId());
        entityManager.remove(cozinha);
    }
}
