package com.danielarrais.algafood.api.assembler.usuario;

import com.danielarrais.algafood.api.controller.usuario.UsuarioController;
import com.danielarrais.algafood.api.dto.output.usuario.UsuarioSimpleOutput;
import com.danielarrais.algafood.domain.model.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsuarioSimpleOutputAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioSimpleOutput> {

    public UsuarioSimpleOutputAssembler() {
        super(UsuarioController.class, UsuarioSimpleOutput.class);
    }

    @Override
    public UsuarioSimpleOutput toModel(Usuario usuario) {
        var usuarioOutput = mapper(usuario, UsuarioSimpleOutput.class);

        usuarioOutput.add(linkTo(methodOn(UsuarioController.class)
                .buscar(usuarioOutput.getId())).withSelfRel());

        usuarioOutput.add(linkTo(methodOn(UsuarioController.class)
                .listar(Pageable.unpaged())).withRel("usuarios"));
        
        return usuarioOutput;
    }

    @Override
    public CollectionModel<UsuarioSimpleOutput> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities).add(linkTo(UsuarioController.class).withSelfRel());
    }
}
