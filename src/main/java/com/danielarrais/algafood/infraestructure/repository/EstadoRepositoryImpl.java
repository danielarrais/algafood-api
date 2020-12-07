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
        var estado = buscar(id);
        entityManager.remove(estado);
    }
}
