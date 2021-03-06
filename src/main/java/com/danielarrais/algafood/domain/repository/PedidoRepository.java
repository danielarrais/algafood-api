package com.danielarrais.algafood.domain.repository;

import com.danielarrais.algafood.domain.model.Pedido;
import com.danielarrais.algafood.domain.repository.custom.BaseSimpleJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends BaseSimpleJpaRepository<Pedido, Long> {

    Optional<Pedido> findByCodigo(String codigo);

    @Query("from Pedido p " +
            "join fetch p.cliente " +
            "join fetch p.restaurante r " +
            "join fetch r.cozinha " +
            "join fetch r.endereco.cidade c " +
            "join fetch c.estado e")
    List<Pedido> findAll();
}

