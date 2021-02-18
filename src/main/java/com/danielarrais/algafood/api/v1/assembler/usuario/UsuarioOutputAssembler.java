package com.danielarrais.algafood.api.v1.assembler.usuario;

import com.danielarrais.algafood.api.v1.controller.usuario.UsuarioController;
import com.danielarrais.algafood.api.v1.dto.output.usuario.UsuarioOutput;
import com.danielarrais.algafood.domain.model.Usuario;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.api.v1.util.LinkBuilder.*;
import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@Component
public class UsuarioOutputAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioOutput> {

    public UsuarioOutputAssembler() {
        super(UsuarioController.class, UsuarioOutput.class);
    }

    @Override
    public UsuarioOutput toModel(Usuario usuario) {
        var usuarioOutput = mapper(usuario, UsuarioOutput.class);

        usuarioOutput.add(linkBuscarUsuario(usuario.getId()));
        usuarioOutput.add(linkUsuarios());
        usuarioOutput.add(linkGrupos(usuario.getId()));

        return usuarioOutput;
    }

    @Override
    public CollectionModel<UsuarioOutput> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities).add(linkUsuarios().withSelfRel());
    }
}
