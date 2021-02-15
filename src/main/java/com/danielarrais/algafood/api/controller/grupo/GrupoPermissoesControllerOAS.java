package com.danielarrais.algafood.api.controller.grupo;

import com.danielarrais.algafood.api.dto.output.permissao.PermissaoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Grupos de Permissões")
interface GrupoPermissoesControllerOAS {

    @ApiOperation("Lista permissões de um grupo")
    List<PermissaoOutput> listar(@ApiParam("ID do grupo") Long grupoId);

    @ApiOperation("Associa uma permissão a um grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Permissão associada ao grupo"),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrada", response = Problem.class)
    })
    void associar(@ApiParam("ID do grupo") Long grupoId, @ApiParam("ID da permissão") Long idPermissao);

    @ApiOperation("Desassocia uma permissão a um grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Permissão desassociada ao grupo"),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrada", response = Problem.class)
    })
    void desassociar(@ApiParam("ID do grupo") Long grupoId, @ApiParam("ID da permissão") Long idPermissao);
}