package com.danielarrais.algafood.api.v1.assembler.cozinha;

import com.danielarrais.algafood.api.v1.controller.cozinha.CozinhaController;
import com.danielarrais.algafood.api.v1.dto.output.cozinha.CozinhaFullOutput;
import com.danielarrais.algafood.domain.model.Cozinha;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.api.v1.util.LinkBuilder.linkBuscarCozinha;
import static com.danielarrais.algafood.api.v1.util.LinkBuilder.linkCozinhas;
import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@Component
public class CozinhaFullOutputAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaFullOutput> {

    public CozinhaFullOutputAssembler() {
        super(CozinhaController.class, CozinhaFullOutput.class);
    }

    @Override
    public CozinhaFullOutput toModel(Cozinha cozinha) {
        var cozinhaFullOutput = mapper(cozinha, CozinhaFullOutput.class);

        cozinhaFullOutput.add(linkBuscarCozinha(cozinha.getId()));
        cozinhaFullOutput.add(linkCozinhas());
        
        return cozinhaFullOutput;
    }

    @Override
    public CollectionModel<CozinhaFullOutput> toCollectionModel(Iterable<? extends Cozinha> cozinhas) {
        return super.toCollectionModel(cozinhas).add(linkCozinhas().withSelfRel());
    }
}
