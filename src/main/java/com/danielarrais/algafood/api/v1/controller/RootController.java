package com.danielarrais.algafood.api.v1.controller;

import com.danielarrais.algafood.api.v1.dto.output.EntryPointOutput;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.danielarrais.algafood.api.v1.util.LinkBuilder.*;

@Api(tags = "Raiz")
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootController {

    @GetMapping
    public EntryPointOutput root() {
        var rootEntryPoint = new EntryPointOutput();

        rootEntryPoint.add(linkCozinhas());
        rootEntryPoint.add(linkPedidos());
        rootEntryPoint.add(linkRestaurantes());
        rootEntryPoint.add(linkGrupos());
        rootEntryPoint.add(linkUsuarios());
        rootEntryPoint.add(linkPermissoes());
        rootEntryPoint.add(linkFormasPagamento());
        rootEntryPoint.add(linkEstados());
        rootEntryPoint.add(linkCidades());
        rootEntryPoint.add(linkEstatisticas());

        return rootEntryPoint;
    }
}