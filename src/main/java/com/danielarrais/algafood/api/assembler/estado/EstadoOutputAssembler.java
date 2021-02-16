package com.danielarrais.algafood.api.assembler.estado;

import com.danielarrais.algafood.api.controller.estado.EstadoController;
import com.danielarrais.algafood.api.controller.usuario.UsuarioController;
import com.danielarrais.algafood.api.dto.output.estado.EstadoOutput;
import com.danielarrais.algafood.domain.model.Estado;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.core.hateoas.LinkBuilder.*;
import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class EstadoOutputAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoOutput> {

    public EstadoOutputAssembler() {
        super(UsuarioController.class, EstadoOutput.class);
    }

    @Override
    public EstadoOutput toModel(Estado estado) {
        var estadoOutput = mapper(estado, EstadoOutput.class);

        estadoOutput.add(linkBuscarEstado(estado.getId()));
        estadoOutput.add(linkEstados());
        
        return estadoOutput;
    }

    @Override
    public CollectionModel<EstadoOutput> toCollectionModel(Iterable<? extends Estado> estados) {
        return super.toCollectionModel(estados).add(linkTo(EstadoController.class).withSelfRel());
    }
}
