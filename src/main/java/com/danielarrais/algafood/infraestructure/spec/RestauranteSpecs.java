package com.danielarrais.algafood.infraestructure.spec;

import com.danielarrais.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

import static com.danielarrais.algafood.domain.model.Restaurante.Fields.taxaFrete;

public class RestauranteSpecs {
    public static Specification<Restaurante> comFreteGratis() {
        return (root, query, builder) -> builder.equal(root.get(taxaFrete), BigDecimal.ZERO);
    }
}
