package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository  extends JpaRepository<Cozinha, Long> {
}
