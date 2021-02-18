package com.danielarrais.algafood.api.v1.controller.grupo;

import com.danielarrais.algafood.api.v1.dto.output.permissao.PermissaoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Grupos de Permissões")
interface GrupoPermissoesControllerOAS {

    @ApiOperation("Lista permissões de um grupo")
    CollectionModel<PermissaoOutput> listar(@ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId);

    @ApiOperation("Associa uma permissão a um grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Permissão associada ao grupo"),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrada", response = Problem.class)
    })
    ResponseEntity<Void> associar(@ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId,
                  @ApiParam(value = "ID da permissão", example = "1", required = true) Long idPermissao);

    @ApiOperation("Desassocia uma permissão a um grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Permissão desassociada ao grupo"),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrada", response = Problem.class)
    })
    ResponseEntity<Void> desassociar(@ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId,
                     @ApiParam(value = "ID da permissão", example = "1", required = true) Long idPermissao);
}