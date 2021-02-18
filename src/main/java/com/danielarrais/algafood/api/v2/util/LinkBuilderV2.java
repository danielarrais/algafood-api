package com.danielarrais.algafood.api.v2.util;

import com.danielarrais.algafood.api.v1.controller.cidade.CidadeController;
import com.danielarrais.algafood.api.v1.controller.estado.EstadoController;
import com.danielarrais.algafood.api.v1.controller.usuario.UsuarioController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;

import static org.springframework.hateoas.TemplateVariable.VariableType.REQUEST_PARAM;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class LinkBuilderV2 {
    private static final TemplateVariables VARIABLES_PAGEABLE = new TemplateVariables(
            new TemplateVariable("page", REQUEST_PARAM),
            new TemplateVariable("size", REQUEST_PARAM),
            new TemplateVariable("sort", REQUEST_PARAM)
    );

    public static Link linkBuscarCidade(Long id) {
        return linkTo(methodOn(CidadeController.class).buscar(id)).withSelfRel();
    }

    public static Link linkCidades() {
        var url = linkTo(methodOn(CidadeController.class).listar(null)).toUri().toString();
        return Link.of(UriTemplate.of(url, VARIABLES_PAGEABLE), "cidades");
    }

    public static Link linkUsuarios(String rel) {
        var url = linkTo(methodOn(UsuarioController.class).listar(null)).toUri().toString();
        return Link.of(UriTemplate.of(url, VARIABLES_PAGEABLE), rel);
    }

    public static Link linkBuscarEstado(Long id) {
        return linkTo(methodOn(EstadoController.class).buscar(id)).withSelfRel();
    }

}
