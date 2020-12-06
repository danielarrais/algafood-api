package com.danielarrais.algafood.infraestructure.repository;

import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Restaurante> all() {
        return entityManager.createQuery("select c from Restaurante as c", Restaurante.class).getResultList();
    }

    @Transactional
    public Restaurante add(Restaurante restaurante) {
        return entityManager.merge(restaurante);
    }

    public Restaurante find(Long id) {
        return entityManager.find(Restaurante.class, id);
    }

    @Transactional
    public void remove(Restaurante restaurante) {
        restaurante = find(restaurante.getId());
        entityManager.remove(restaurante);
    }
}
