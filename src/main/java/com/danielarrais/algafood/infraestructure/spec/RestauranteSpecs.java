package com.danielarrais.algafood.infraestructure.spec;

import com.danielarrais.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteSpecs {
    public static Specification<Restaurante> comFreteGratis() {
        return (root, query, builder) -> builder.equal(root.get(Restaurante.Fields.taxaFrete), BigDecimal.ZERO);
    }
}
