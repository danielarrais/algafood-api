package com.danielarrais.algafood.api.v1.assembler.permissao;

import com.danielarrais.algafood.api.v1.controller.permissao.PermissaoController;
import com.danielarrais.algafood.api.v1.dto.output.permissao.PermissaoOutput;
import com.danielarrais.algafood.api.v1.util.LinkBuilder;
import com.danielarrais.algafood.domain.model.Permissao;
import com.danielarrais.algafood.util.ModelMapperUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.danielarrais.algafood.api.v1.util.LinkBuilder.*;

@Component
public class PermissaoOutputAssembler extends RepresentationModelAssemblerSupport<Permissao, PermissaoOutput> {

    public PermissaoOutputAssembler() {
        super(PermissaoController.class, PermissaoOutput.class);
    }

    @Override
    public PermissaoOutput toModel(Permissao permissao) {
        var permissaoDTO = ModelMapperUtils.mapper(permissao, PermissaoOutput.class);

        permissaoDTO.add(LinkBuilder.linkPermissoes());
        permissaoDTO.add(LinkBuilder.linkBuscarPermissao(permissao.getId()));

        return permissaoDTO;
    }

    @Override
    public CollectionModel<PermissaoOutput> toCollectionModel(Iterable<? extends Permissao> entities) {
        return super.toCollectionModel(entities).add(LinkBuilder.linkPermissoes().withSelfRel());
    }

    public PermissaoOutput toModel(Long idGrupo, Permissao permissao) {
        var permissaoDTO = toModel(permissao);

        return permissaoDTO.add(linkDesassociarPermissaoGrupo(idGrupo, permissao.getId()));
    }

    public CollectionModel<PermissaoOutput> toCollectionModel(Long idGrupo, Iterable<? extends Permissao> entities) {
        var models = new ArrayList<PermissaoOutput>();

        entities.forEach(permissao -> {
            models.add(toModel(idGrupo, permissao));
        });

        return CollectionModel.of(models)
                .add(LinkBuilder.linkPermissoes(idGrupo))
                .add(linkAssociarPermissaoGrupo(idGrupo));
    }
}