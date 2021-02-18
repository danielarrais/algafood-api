package com.danielarrais.algafood.api.v1.assembler.cidade;

import com.danielarrais.algafood.api.v1.controller.cidade.CidadeController;
import com.danielarrais.algafood.api.v1.dto.output.cidade.CidadeOutput;
import com.danielarrais.algafood.api.v1.util.LinkBuilder;
import com.danielarrais.algafood.domain.model.Cidade;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.api.v1.util.LinkBuilder.linkBuscarCidade;
import static com.danielarrais.algafood.api.v1.util.LinkBuilder.linkCidades;
import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@Component
public class CidadeOutputAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeOutput> {
    public CidadeOutputAssembler() {
        super(CidadeController.class, CidadeOutput.class);
    }

    @Override
    public CidadeOutput toModel(Cidade cidade) {
        var cidadeOutput = mapper(cidade, CidadeOutput.class);

        var estado = cidadeOutput.getEstado();
        var idEstado = estado.getId();

        cidadeOutput.add(linkBuscarCidade(cidade.getId()));
        cidadeOutput.add(linkCidades());

        estado.add(LinkBuilder.linkBuscarEstado(idEstado));

        return cidadeOutput;
    }

    @Override
    public CollectionModel<CidadeOutput> toCollectionModel(Iterable<? extends Cidade> entities) {
        return super.toCollectionModel(entities).add(linkCidades().withSelfRel());
    }
}
