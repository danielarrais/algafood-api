package com.danielarrais.algafood.api.v1.assembler.usuario;

import com.danielarrais.algafood.api.v1.controller.usuario.UsuarioController;
import com.danielarrais.algafood.api.v1.dto.output.usuario.UsuarioSimpleOutput;
import com.danielarrais.algafood.api.v1.util.LinkBuilder;
import com.danielarrais.algafood.domain.model.Usuario;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.danielarrais.algafood.api.v1.util.LinkBuilder.*;
import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@Component
public class UsuarioSimpleOutputAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioSimpleOutput> {

    public UsuarioSimpleOutputAssembler() {
        super(UsuarioController.class, UsuarioSimpleOutput.class);
    }

    @Override
    public UsuarioSimpleOutput toModel(Usuario usuario) {
        var usuarioOutput = mapper(usuario, UsuarioSimpleOutput.class);

        usuarioOutput.add(linkBuscarUsuario(usuario.getId()));
        usuarioOutput.add(linkUsuarios());
        
        return usuarioOutput;
    }

    @Override
    public CollectionModel<UsuarioSimpleOutput> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities).add(linkUsuarios().withSelfRel());
    }

    public UsuarioSimpleOutput toModel(Long idRestaurante, Usuario usuario) {
        var usuarioOutput = toModel(usuario);

        usuarioOutput.add(linkDesassociarResponsavelRestaurante(idRestaurante, usuario.getId()));

        return usuarioOutput;
    }

    public CollectionModel<UsuarioSimpleOutput> toCollectionModel(Long idRestaurante, Iterable<? extends Usuario> entities) {
        var models = new ArrayList<UsuarioSimpleOutput>();

        entities.forEach(formaPagamento -> {
            models.add(toModel(idRestaurante, formaPagamento));
        });

        return CollectionModel.of(models)
                .add(LinkBuilder.linkResponsaveisRestaurante(idRestaurante))
                .add(linkAssociarResponsavelRestaurante(idRestaurante));
    }
}
