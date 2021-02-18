package com.danielarrais.algafood.api.v2.util;

import com.danielarrais.algafood.api.v2.controller.cidade.CidadeControllerV2;
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
        return linkTo(methodOn(CidadeControllerV2.class).buscar(id)).withSelfRel();
    }

    public static Link linkCidades() {
        var url = linkTo(methodOn(CidadeControllerV2.class).listar(null)).toUri().toString();
        return Link.of(UriTemplate.of(url, VARIABLES_PAGEABLE), "cidades");
    }
}
