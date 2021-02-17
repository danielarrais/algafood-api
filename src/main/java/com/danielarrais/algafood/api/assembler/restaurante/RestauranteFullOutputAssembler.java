package com.danielarrais.algafood.api.assembler.restaurante;

import com.danielarrais.algafood.api.controller.restaurante.RestauranteController;
import com.danielarrais.algafood.api.dto.output.restaurante.RestauranteFullOutput;
import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.util.ModelMapperUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.core.hateoas.LinkBuilder.*;

@Component
public class RestauranteFullOutputAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteFullOutput> {

    public RestauranteFullOutputAssembler() {
        super(RestauranteController.class, RestauranteFullOutput.class);
    }

    @Override
    public RestauranteFullOutput toModel(Restaurante restaurante) {
        var restauranteDTO = ModelMapperUtils.mapper(restaurante, RestauranteFullOutput.class);

        var cozinha = restauranteDTO.getCozinha();
        var cidade = restauranteDTO.getEndereco().getCidade();

        restauranteDTO.add(linkBuscarRestaurante(restaurante.getId()));
        restauranteDTO.add(linkRestaurantes());
        restauranteDTO.add(linkProdutos(restaurante.getId()));
        restauranteDTO.add(linkResponsaveisRestaurante(restaurante.getId()));
        restauranteDTO.add(linkFormasPagamentoRestaurante(restaurante.getId()));

        if (restaurante.getAtivo()) {
            restauranteDTO.add(linkInativarRestaurante(restaurante.getId()));
        } else {
            restauranteDTO.add(linkAtivarRestaurante(restaurante.getId()));
        }

        if (restaurante.getAberto()) {
            restauranteDTO.add(linkFecharRestaurante(restaurante.getId()));
        } else {
            restauranteDTO.add(linkAbrirRestaurante(restaurante.getId()));
        }

        cozinha.add(linkBuscarCozinha(cozinha.getId()));
        cidade.add(linkBuscarCidade(cidade.getId()));

        return restauranteDTO;
    }

    @Override
    public CollectionModel<RestauranteFullOutput> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .add(linkRestaurantes().withSelfRel());
    }
}
