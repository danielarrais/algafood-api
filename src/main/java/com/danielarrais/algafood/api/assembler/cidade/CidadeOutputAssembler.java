package com.danielarrais.algafood.api.assembler.cidade;

import com.danielarrais.algafood.api.controller.cidade.CidadeController;
import com.danielarrais.algafood.api.controller.estado.EstadoController;
import com.danielarrais.algafood.api.dto.output.cidade.CidadeOutput;
import com.danielarrais.algafood.domain.model.Cidade;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CidadeOutputAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeOutput> {
    public CidadeOutputAssembler() {
        super(CidadeController.class, CidadeOutput.class);
    }

    @Override
    public CidadeOutput toModel(Cidade cidade) {
        var cidadeOutput = mapper(cidade, CidadeOutput.class);

        cidadeOutput.add(linkTo(methodOn(CidadeController.class)
                .buscar(cidadeOutput.getId())).withSelfRel());

        cidadeOutput.add(linkTo(methodOn(CidadeController.class)
                .listar(Pageable.unpaged())).withRel("cidades"));

        cidadeOutput.getEstado().add(linkTo(methodOn(EstadoController.class)
                .buscar(cidadeOutput.getEstado().getId())).withSelfRel());

        return cidadeOutput;
    }

    @Override
    public CollectionModel<CidadeOutput> toCollectionModel(Iterable<? extends Cidade> entities) {
        return super.toCollectionModel(entities).add(linkTo(CidadeController.class).withSelfRel());
    }
}
