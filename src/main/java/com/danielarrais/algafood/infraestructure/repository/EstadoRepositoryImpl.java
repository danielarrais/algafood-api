package com.danielarrais.algafood.infraestructure.repository;

import com.danielarrais.algafood.domain.model.Estado;
import com.danielarrais.algafood.domain.repository.EstadoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Estado> listar() {
        return entityManager.createQuery("select c from Estado as c", Estado.class).getResultList();
    }

    @Transactional
    public void salvar(Estado estado) {
        entityManager.merge(estado);
    }

    public Estado buscar(Long id) {
        return entityManager.find(Estado.class, id);
    }

    @Transactional
    public void remover(Long id) {
        Estado estado = Optional
                .ofNullable(buscar(id))
                .orElseThrow(() -> {
                    // Esperava a existência de 1 cozinha
                    throw new EmptyResultDataAccessException(1);
                });

        entityManager.remove(estado);

    }
}
