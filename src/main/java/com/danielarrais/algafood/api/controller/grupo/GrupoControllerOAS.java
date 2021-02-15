package com.danielarrais.algafood.api.controller.grupo;

import com.danielarrais.algafood.api.dto.input.grupo.GrupoInput;
import com.danielarrais.algafood.api.dto.output.grupo.GrupoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

@Api(tags = "Grupos de Permissões")
interface GrupoControllerOAS {

    @ApiOperation("Lista grupos de permissão")
    Page<GrupoOutput> listar(Pageable pageable);

    @ApiOperation("Busca grupo de permissão")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    GrupoOutput buscar(Long id);

    @ApiOperation("Adiciona grupo de permissão")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Grupo cadastrado", response = Problem.class)
    })
    void adicionar(GrupoInput grupoInput);

    @ApiOperation("Atualiza grupo de permissão")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Grupo atualizado"),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    void atualizar(Long id, GrupoInput grupoInput);

    @ApiOperation("Atualiza parcialmente grupo de permissão")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Grupo atualizado"),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    void atualizarParcial(Long id, Map<String, Object> valores);

    @ApiOperation("Exclui grupo de permissão")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Grupo de permissão excluido"),
            @ApiResponse(code = 404, message = "Grupo de permissão não encontrado", response = Problem.class)
    })
    void remover(Long id);
}