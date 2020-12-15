package com.danielarrais.algafood.infraestructure.repository;

import com.danielarrais.algafood.domain.model.Permissao;
import com.danielarrais.algafood.domain.repository.PermissaoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Permissao> listar() {
        return entityManager.createQuery("select c from Permissao as c", Permissao.class).getResultList();
    }

    @Transactional
    public void salvar(Permissao permissao) {
        entityManager.merge(permissao);
    }

    public Permissao buscar(Long id) {
        return entityManager.find(Permissao.class, id);
    }

    @Transactional
    public void remover(Long id) {
        Permissao permissao = Optional
                .ofNullable(buscar(id))
                .orElseThrow(() -> {
                    // Esperava a existência de 1 cozinha
                    throw new EmptyResultDataAccessException(1);
                });

        entityManager.remove(permissao);
    }
}
