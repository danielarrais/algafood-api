package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<Restaurante, Long> {

}
