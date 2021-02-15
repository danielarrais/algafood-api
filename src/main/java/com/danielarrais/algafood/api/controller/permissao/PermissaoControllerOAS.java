package com.danielarrais.algafood.api.controller.permissao;

import com.danielarrais.algafood.api.dto.input.permissao.PermissaoInput;
import com.danielarrais.algafood.api.dto.output.permissao.PermissaoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Api(tags = "Permissões")
interface PermissaoControllerOAS {

    @ApiOperation("Lista permissões")
    Page<PermissaoOutput> listar(Pageable pageable);

    @ApiOperation("Busca permissão")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da permissão inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Permissão não encontrada", response = Problem.class)
    })
    PermissaoOutput buscar(@ApiParam(value = "ID da permissão", example = "1", required = true) Long id);

    @ApiOperation("Adiciona permissão")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Permissão cadastrada", response = Problem.class)
    })
    void adicionar(@ApiParam("Corpo") PermissaoInput permissaoInput);

    @ApiOperation("Atualiza permissão")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Permissão atualizada"),
            @ApiResponse(code = 404, message = "Permissão não encontrada", response = Problem.class)
    })
    void atualizar(@ApiParam(value = "ID da permissão", example = "1", required = true) Long id,
                   @ApiParam("Corpo") PermissaoInput permissaoInput);

    @ApiOperation("Exclui permissão")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Permissão excluida"),
            @ApiResponse(code = 404, message = "Permissão não encontrada", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID da permissão", example = "1", required = true) Long id);
}