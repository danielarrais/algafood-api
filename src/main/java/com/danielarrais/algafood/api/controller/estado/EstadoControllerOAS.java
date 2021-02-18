package com.danielarrais.algafood.api.controller.estado;

import com.danielarrais.algafood.api.dto.input.estado.EstadoInput;
import com.danielarrais.algafood.api.dto.output.estado.EstadoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Estados")
interface EstadoControllerOAS {

    @ApiOperation("Lista estados")
    CollectionModel<EstadoOutput> listar(Pageable pageable);

    @ApiOperation("Busca um estado")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do estado inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    EstadoOutput buscar(@ApiParam(value = "ID do estado", example = "1", required = true) Long id);

    @ApiOperation("Adiciona um estado")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Estado cadastrado", response = Problem.class)
    })
    void adicionar(@ApiParam("Corpo") EstadoInput estadoInput);

    @ApiOperation("Atualiza um estado")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Estado atualizado"),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    void atualizar(@ApiParam(value = "ID do estado", example = "1", required = true) Long id, @ApiParam("Corpo") EstadoInput estadoInput);

    @ApiOperation("Exclui um estado")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Estado excluido"),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID do estado", example = "1", required = true) Long id);
}