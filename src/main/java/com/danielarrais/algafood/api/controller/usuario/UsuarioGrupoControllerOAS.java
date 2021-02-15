package com.danielarrais.algafood.api.controller.usuario;

import com.danielarrais.algafood.api.dto.output.permissao.PermissaoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Grupos de Permissões do Usuário")
interface UsuarioGrupoControllerOAS {

    @ApiOperation("Lista grupos de permissões de um usuário")
    List<PermissaoOutput> listar(@ApiParam("ID do usuário") Long usuarioId);

    @ApiOperation("Associa grupo de permissões a um usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário associado ao grupo de permissões"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    void associar(@ApiParam("ID do usuário") Long usuarioId, @ApiParam("ID do grupo") Long idGrupo);

    @ApiOperation("Desassocia grupo de permissões a um usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário desassociado ao grupo de permissões"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    void desassociar(@ApiParam("ID do usuário") Long usuarioId, @ApiParam("ID do grupo") Long idGrupo);
}