package com.danielarrais.algafood.infraestructure.repository;

import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.RestauranteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Restaurante> listar() {
        return entityManager.createQuery("select c from Restaurante as c", Restaurante.class).getResultList();
    }

    @Transactional
    public void salvar(Restaurante restaurante) {
        entityManager.merge(restaurante);
    }

    public Restaurante buscar(Long id) {
        return entityManager.find(Restaurante.class, id);
    }

    @Transactional
    public void remover(Long id) {
        Restaurante restaurante = Optional
                .ofNullable(buscar(id))
                .orElseThrow(() -> {
                    // Esperava a existência de 1 cozinha
                    throw new EmptyResultDataAccessException(1);
                });

        entityManager.remove(restaurante);
    }
}
