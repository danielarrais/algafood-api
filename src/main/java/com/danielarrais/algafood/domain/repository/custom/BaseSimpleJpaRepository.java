package com.danielarrais.algafood.domain.repository.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseSimpleJpaRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    Optional<T> findFirst();
}
