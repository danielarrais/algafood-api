package com.danielarrais.algafood.api.assembler.grupo;

import com.danielarrais.algafood.api.controller.grupo.GrupoController;
import com.danielarrais.algafood.api.dto.output.grupo.GrupoOutput;
import com.danielarrais.algafood.domain.model.Grupo;
import com.danielarrais.algafood.util.ModelMapperUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.danielarrais.algafood.core.hateoas.LinkBuilder.*;

@Component
public class GrupoOutputAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoOutput> {

    public GrupoOutputAssembler() {
        super(GrupoController.class, GrupoOutput.class);
    }

    @Override
    public GrupoOutput toModel(Grupo grupo) {
        var grupoDTO = ModelMapperUtils.mapper(grupo, GrupoOutput.class);

        var permissoes = grupoDTO.getPermissoes();

        grupoDTO.add(linkGrupos());
        grupoDTO.add(linkBuscarGrupo(grupoDTO.getId()));
        grupoDTO.add(linkPermissoes(grupoDTO.getId()));

        permissoes.forEach(permissao -> {
            permissao.add(linkBuscarGrupo(permissao.getId()));
        });

        return grupoDTO;
    }

    public GrupoOutput toModel(Long idUsuario, Grupo grupo) {
        var grupoDTO = toModel(grupo);

        return grupoDTO.add(linkDesassociarGrupoUsuario(idUsuario, grupo.getId()));
    }

    public CollectionModel<GrupoOutput> toCollectionModel(Long idUsuario, Iterable<? extends Grupo> entities) {
        var models = new ArrayList<GrupoOutput>();

        entities.forEach(grupo -> {
            models.add(toModel(idUsuario, grupo));
        });

        return CollectionModel.of(models)
                .add(linkGrupos(idUsuario))
                .add(linkAssociarGrupoUsuario(idUsuario));
    }
}