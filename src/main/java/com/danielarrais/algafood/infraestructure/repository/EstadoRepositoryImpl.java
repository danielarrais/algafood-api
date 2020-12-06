package com.danielarrais.algafood.infraestructure.repository;

import com.danielarrais.algafood.domain.model.Estado;
import com.danielarrais.algafood.domain.repository.EstadoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Estado> all() {
        return entityManager.createQuery("select c from Estado as c", Estado.class).getResultList();
    }

    @Transactional
    public Estado add(Estado estado) {
        return entityManager.merge(estado);
    }

    public Estado find(Long id) {
        return entityManager.find(Estado.class, id);
    }

    @Transactional
    public void remove(Estado estado) {
        estado = find(estado.getId());
        entityManager.remove(estado);
    }
}
