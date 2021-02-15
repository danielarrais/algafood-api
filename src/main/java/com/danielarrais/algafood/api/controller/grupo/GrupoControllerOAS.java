package com.danielarrais.algafood.api.controller.grupo;

import com.danielarrais.algafood.api.dto.input.grupo.GrupoInput;
import com.danielarrais.algafood.api.dto.output.grupo.GrupoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Api(tags = "Grupos de Permissões")
interface GrupoControllerOAS {

    @ApiOperation("Lista grupos de permissão")
    Page<GrupoOutput> listar(Pageable pageable);

    @ApiOperation("Busca grupo de permissão")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    GrupoOutput buscar(@ApiParam("ID do grupo") Long id);

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
    void atualizar(@ApiParam("ID do grupo") Long id, @ApiParam("Corpo") GrupoInput grupoInput);

    @ApiOperation("Exclui grupo de permissão")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Grupo de permissão excluido"),
            @ApiResponse(code = 404, message = "Grupo de permissão não encontrado", response = Problem.class)
    })
    void remover(@ApiParam("ID do grupo") Long id);
}