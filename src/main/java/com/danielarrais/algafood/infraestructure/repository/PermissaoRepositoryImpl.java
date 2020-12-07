package com.danielarrais.algafood.infraestructure.repository;

import com.danielarrais.algafood.domain.model.Permissao;
import com.danielarrais.algafood.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
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
    public void remover(Permissao permissao) {
        permissao = buscar(permissao.getId());
        entityManager.remove(permissao);
    }
}
