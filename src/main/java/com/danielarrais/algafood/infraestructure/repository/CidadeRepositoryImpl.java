package com.danielarrais.algafood.infraestructure.repository;

import com.danielarrais.algafood.domain.model.Cidade;
import com.danielarrais.algafood.domain.repository.CidadeRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cidade> all() {
        return entityManager.createQuery("select c from Cidade as c", Cidade.class).getResultList();
    }

    @Transactional
    public Cidade add(Cidade cidade) {
        return entityManager.merge(cidade);
    }

    public Cidade find(Long id) {
        return entityManager.find(Cidade.class, id);
    }

    @Transactional
    public void remove(Cidade cidade) {
        cidade = find(cidade.getId());
        entityManager.remove(cidade);
    }
}
