package com.danielarrais.algafood.api.v1.controller.grupo;

import com.danielarrais.algafood.api.v1.dto.input.grupo.GrupoInput;
import com.danielarrais.algafood.api.v1.dto.output.grupo.GrupoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Grupos de Permissões")
interface GrupoControllerOAS {

    @ApiOperation("Lista grupos de permissão")
    PagedModel<GrupoOutput> listar(Pageable pageable);

    @ApiOperation("Busca grupo de permissão")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    GrupoOutput buscar(@ApiParam(value = "ID do grupo", example = "1", required = true) Long id);

    @ApiOperation("Adiciona grupo de permissão")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Grupo cadastrado", response = Problem.class)
    })
    void adicionar(@ApiParam("Corpo") GrupoInput grupoInput);

    @ApiOperation("Atualiza grupo de permissão")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Grupo atualizado"),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    void atualizar(@ApiParam(value = "ID do grupo", example = "1", required = true) Long id, @ApiParam("Corpo") GrupoInput grupoInput);

    @ApiOperation("Exclui grupo de permissão")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Grupo de permissão excluido"),
            @ApiResponse(code = 404, message = "Grupo de permissão não encontrado", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID do grupo", example = "1", required = true) Long id);
}