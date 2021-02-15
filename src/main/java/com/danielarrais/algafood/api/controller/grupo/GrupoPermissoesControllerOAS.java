package com.danielarrais.algafood.api.controller.grupo;

import com.danielarrais.algafood.api.dto.output.permissao.PermissaoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(tags = "Grupos de Permissões")
interface GrupoPermissoesControllerOAS {
    List<PermissaoOutput> listar(Long grupoId);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Permissão associada ao grupo"),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrada", response = Problem.class)
    })
    void associar(Long grupoId, Long idPermissao);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Permissão desassociada ao grupo"),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrada", response = Problem.class)
    })
    void desassociar(Long grupoId, Long idPermissao);
}