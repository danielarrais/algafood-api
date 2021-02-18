package com.danielarrais.algafood.api.v1.controller.usuario;

import com.danielarrais.algafood.api.exception.Problem;
import com.danielarrais.algafood.api.v1.dto.output.grupo.GrupoOutput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Grupos de Permissões do Usuário")
interface UsuarioGrupoControllerOAS {

    @ApiOperation("Lista grupos de permissões de um usuário")
    CollectionModel<GrupoOutput> listar(@ApiParam(value = "ID do usuário") Long usuarioId);

    @ApiOperation("Associa grupo de permissões a um usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário associado ao grupo de permissões"),
            @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> associar(@ApiParam(value = "ID do usuário") Long usuarioId, @ApiParam(value = "ID do grupo") Long idGrupo);

    @ApiOperation("Desassocia grupo de permissões a um usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Usuário desassociado ao grupo de permissões"),
            @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> desassociar(@ApiParam(value = "ID do usuário") Long usuarioId, @ApiParam(value = "ID do grupo") Long idGrupo);
}