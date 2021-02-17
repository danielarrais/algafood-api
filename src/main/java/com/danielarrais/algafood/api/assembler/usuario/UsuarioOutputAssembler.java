package com.danielarrais.algafood.api.assembler.usuario;

import com.danielarrais.algafood.api.controller.usuario.UsuarioController;
import com.danielarrais.algafood.api.dto.output.usuario.UsuarioOutput;
import com.danielarrais.algafood.domain.model.Usuario;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.core.hateoas.LinkBuilder.linkBuscarUsuario;
import static com.danielarrais.algafood.core.hateoas.LinkBuilder.linkUsuarios;
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
        
        return usuarioOutput;
    }

    @Override
    public CollectionModel<UsuarioOutput> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities).add(linkUsuarios().withSelfRel());
    }
}