package com.danielarrais.algafood.api.controller.cozinha;

import com.danielarrais.algafood.api.dto.input.cozinha.CozinhaInput;
import com.danielarrais.algafood.api.dto.output.cozinha.CozinhaFullOutput;
import com.danielarrais.algafood.api.dto.output.cozinha.CozinhaOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;

@Api(tags = "Cozinhas")
interface CozinhaControllerOas {
    @ApiOperation("Lista as cozinhas")
    Page<CozinhaOutput> listar(Pageable pageable);

    @ApiOperation("Busca uma cozinha")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cozinha inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    CozinhaFullOutput buscar(@ApiParam(value = "ID da cozinha", example = "1", required = true) Long id);

    @ApiOperation("Adiciona uma cozinha")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cozinha cadastrada", response = Problem.class)
    })
    void adicionar(@ApiParam("Corpo") CozinhaInput cozinhaInput);

    @ApiOperation("Atualiza uma cozinha")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cozinha atualizada"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    void atualizar(@ApiParam(value = "ID da cozinha", example = "1", required = true) Long id, @ApiParam("Corpo") CozinhaInput cozinhaInput);

    @ApiOperation("Deleta uma cozinha")
    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cozinha excluida"),
            @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID da cozinha", example = "1", required = true) Long id);
}