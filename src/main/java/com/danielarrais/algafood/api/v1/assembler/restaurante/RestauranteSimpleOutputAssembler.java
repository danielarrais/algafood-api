package com.danielarrais.algafood.api.v1.assembler.restaurante;

import com.danielarrais.algafood.api.v1.controller.restaurante.RestauranteController;
import com.danielarrais.algafood.api.v1.dto.output.restaurante.RestauranteSimpleOutput;
import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.util.ModelMapperUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.api.v1.util.LinkBuilder.*;

@Component
public class RestauranteSimpleOutputAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteSimpleOutput> {

    public RestauranteSimpleOutputAssembler() {
        super(RestauranteController.class, RestauranteSimpleOutput.class);
    }

    @Override
    public RestauranteSimpleOutput toModel(Restaurante restaurante) {
        var restauranteDTO = ModelMapperUtils.mapper(restaurante, RestauranteSimpleOutput.class);

        var cozinha = restauranteDTO.getCozinha();

        restauranteDTO.add(linkBuscarRestaurante(restaurante.getId()));
        restauranteDTO.add(linkRestaurantes());

        cozinha.add(linkBuscarCozinha(cozinha.getId()));

        return restauranteDTO;
    }

    @Override
    public CollectionModel<RestauranteSimpleOutput> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities).add(linkRestaurantes().withSelfRel());
    }
}
