package com.danielarrais.algafood.infraestructure.repository;

import com.danielarrais.algafood.domain.repository.custom.CustomSimpleJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CustomSimpleJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomSimpleJpaRepository<T, ID> {

    private EntityManager entityManager;

    public CustomSimpleJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> findFirst() {
        String jpql = String.format("from %s order by id asc", getDomainClass().getName());

        T entity = entityManager.createQuery(jpql, getDomainClass())
                .setMaxResults(1)
                .getSingleResult();

        return Optional.ofNullable(entity);
    }
}
