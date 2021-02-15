package com.danielarrais.algafood.api.controller.grupo;

import com.danielarrais.algafood.api.dto.output.permissao.PermissaoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(tags = "Grupos de Permissões")
interface GrupoPermissoesControllerOAS {

    @ApiOperation("Lista permissões de um grupo")
    List<PermissaoOutput> listar(Long grupoId);

    @ApiOperation("Associa uma permissão a um grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Permissão associada ao grupo"),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrada", response = Problem.class)
    })
    void associar(Long grupoId, Long idPermissao);

    @ApiOperation("Desassocia uma permissão a um grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Permissão desassociada ao grupo"),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrada", response = Problem.class)
    })
    void desassociar(Long grupoId, Long idPermissao);
}