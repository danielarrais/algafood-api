package com.danielarrais.algafood.infraestructure.spec;

import com.danielarrais.algafood.api.dto.input.pedido.filter.PedidoFilter;
import com.danielarrais.algafood.domain.model.Pedido;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Objects;

public class PedidoSpecs {
    public static Specification<Pedido> usandoFiltro(PedidoFilter filtro) {
        return (root, query, builder) -> {
            root.fetch("restaurante");
            root.fetch("cliente");

            var predicates = new ArrayList<Predicate>();

            if (Objects.nonNull(filtro.getClienteId())) {
                var predicate = builder.equal(root.get("cliente"), filtro.getClienteId());
                predicates.add(predicate);
            }

            if (Objects.nonNull(filtro.getRestauranteId())) {
                var predicate = builder.equal(root.get("restaurante"), filtro.getRestauranteId());
                predicates.add(predicate);
            }

            if (Objects.nonNull(filtro.getDataCriacaoInicio())) {
                var predicate = builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio());
                predicates.add(predicate);
            }

            if (Objects.nonNull(filtro.getDataCriacaoFim())) {
                var predicate = builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim());
                predicates.add(predicate);
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
