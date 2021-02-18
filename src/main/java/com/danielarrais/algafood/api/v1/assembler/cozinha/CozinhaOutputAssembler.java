package com.danielarrais.algafood.api.v1.assembler.cozinha;

import com.danielarrais.algafood.api.v1.controller.cozinha.CozinhaController;
import com.danielarrais.algafood.api.v1.dto.output.cozinha.CozinhaOutput;
import com.danielarrais.algafood.domain.model.Cozinha;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.api.v1.util.LinkBuilder.linkBuscarCozinha;
import static com.danielarrais.algafood.api.v1.util.LinkBuilder.linkCozinhas;
import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@Component
public class CozinhaOutputAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaOutput> {

    public CozinhaOutputAssembler() {
        super(CozinhaController.class, CozinhaOutput.class);
    }

    @Override
    public CozinhaOutput toModel(Cozinha cozinha) {
        var cozinhaOutput = mapper(cozinha, CozinhaOutput.class);

        cozinhaOutput.add(linkBuscarCozinha(cozinha.getId()));
        cozinhaOutput.add(linkCozinhas());
        
        return cozinhaOutput;
    }

    @Override
    public CollectionModel<CozinhaOutput> toCollectionModel(Iterable<? extends Cozinha> cozinhas) {
        return super.toCollectionModel(cozinhas).add(linkCozinhas().withSelfRel());
    }
}
