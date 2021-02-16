package com.danielarrais.algafood.api.assembler.cozinha;

import com.danielarrais.algafood.api.controller.cozinha.CozinhaController;
import com.danielarrais.algafood.api.dto.output.cozinha.CozinhaOutput;
import com.danielarrais.algafood.domain.model.Cozinha;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CozinhaOutputAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaOutput> {

    public CozinhaOutputAssembler() {
        super(CozinhaController.class, CozinhaOutput.class);
    }

    @Override
    public CozinhaOutput toModel(Cozinha cozinha) {
        var cozinhaOutput = mapper(cozinha, CozinhaOutput.class);

        cozinhaOutput.add(linkTo(methodOn(CozinhaController.class)
                .buscar(cozinhaOutput.getId())).withSelfRel());

        cozinhaOutput.add(linkTo(methodOn(CozinhaController.class)
                .listar(Pageable.unpaged())).withRel("cozinhas"));
        
        return cozinhaOutput;
    }

    @Override
    public CollectionModel<CozinhaOutput> toCollectionModel(Iterable<? extends Cozinha> cozinhas) {
        return super.toCollectionModel(cozinhas).add(linkTo(CozinhaController.class).withSelfRel());
    }
}
