package com.danielarrais.algafood.infraestructure.repository;

import com.danielarrais.algafood.domain.model.FotoProduto;
import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.custom.ProdutoRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class ProdutoRepositoryImpl extends SimpleJpaRepository<Restaurante, Long> implements ProdutoRepositoryCustom {
    private final EntityManager entityManager;

    @Autowired
    public ProdutoRepositoryImpl(EntityManager entityManager) {
        super(Restaurante.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public FotoProduto saveAndFlush(FotoProduto fotoProduto) {
        var savedFotoProduto = entityManager.merge(fotoProduto);
        entityManager.flush();

        return savedFotoProduto;
    }

    @Override
    public void delete(FotoProduto fotoProduto) {
        entityManager.remove(fotoProduto);
    }
}
