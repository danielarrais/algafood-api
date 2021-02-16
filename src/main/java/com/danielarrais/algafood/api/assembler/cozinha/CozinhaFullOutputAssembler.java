package com.danielarrais.algafood.api.assembler.cozinha;

import com.danielarrais.algafood.api.controller.cozinha.CozinhaController;
import com.danielarrais.algafood.api.dto.output.cozinha.CozinhaFullOutput;
import com.danielarrais.algafood.domain.model.Cozinha;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CozinhaFullOutputAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaFullOutput> {

    public CozinhaFullOutputAssembler() {
        super(CozinhaController.class, CozinhaFullOutput.class);
    }

    @Override
    public CozinhaFullOutput toModel(Cozinha cozinha) {
        var cozinhaFullOutput = mapper(cozinha, CozinhaFullOutput.class);

        cozinhaFullOutput.add(linkTo(methodOn(CozinhaController.class)
                .buscar(cozinhaFullOutput.getId())).withSelfRel());

        cozinhaFullOutput.add(linkTo(methodOn(CozinhaController.class)
                .listar(Pageable.unpaged())).withRel("cozinhas"));
        
        return cozinhaFullOutput;
    }

    @Override
    public CollectionModel<CozinhaFullOutput> toCollectionModel(Iterable<? extends Cozinha> cozinhas) {
        return super.toCollectionModel(cozinhas).add(linkTo(CozinhaController.class).withSelfRel());
    }
}
