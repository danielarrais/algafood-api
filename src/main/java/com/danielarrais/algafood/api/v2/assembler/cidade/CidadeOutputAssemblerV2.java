package com.danielarrais.algafood.api.v2.assembler.cidade;

import com.danielarrais.algafood.api.v2.controller.cidade.CidadeControllerV2;
import com.danielarrais.algafood.api.v2.dto.output.cidade.CidadeOutputV2;
import com.danielarrais.algafood.domain.model.Cidade;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.api.v2.util.LinkBuilderV2.*;
import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@Component
public class CidadeOutputAssemblerV2 extends RepresentationModelAssemblerSupport<Cidade, CidadeOutputV2> {
    public CidadeOutputAssemblerV2() {
        super(CidadeControllerV2.class, CidadeOutputV2.class);
    }

    @Override
    public CidadeOutputV2 toModel(Cidade cidade) {
        var cidadeOutput = mapper(cidade, CidadeOutputV2.class);

        cidadeOutput.add(linkBuscarCidade(cidade.getId()));
        cidadeOutput.add(linkCidades());

        return cidadeOutput;
    }

    @Override
    public CollectionModel<CidadeOutputV2> toCollectionModel(Iterable<? extends Cidade> entities) {
        return super.toCollectionModel(entities).add(linkCidades().withSelfRel());
    }
}
