package com.danielarrais.algafood.api.controller.usuario;

import com.danielarrais.algafood.api.dto.output.permissao.PermissaoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(tags = "Grupos de Permissões do Usuário")
interface UsuarioGrupoControllerOAS {

    List<PermissaoOutput> listar(Long usuarioId);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário associado ao grupo de permissões"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    void associar(Long usuarioId, Long idGrupo);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário desassociado ao grupo de permissões"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    void desassociar(Long usuarioId, Long idGrupo);
}