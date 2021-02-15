package com.danielarrais.algafood.api.controller.estado;

import com.danielarrais.algafood.api.dto.input.estado.EstadoInput;
import com.danielarrais.algafood.api.dto.output.estado.EstadoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

@Api(tags = "Estados")
interface EstadoControllerOAS {

    @ApiOperation("Lista estados")
    Page<EstadoOutput> listar(Pageable pageable);

    @ApiOperation("Busca um estado")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do estado inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    EstadoOutput buscar(Long id);

    @ApiOperation("Adiciona um estado")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Estado cadastrado", response = Problem.class)
    })
    void adicionar(EstadoInput estadoInput);

    @ApiOperation("Atualiza um estado")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Estado atualizado"),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    void atualizar(Long id, EstadoInput estadoInput);

    @ApiOperation("Atualiza parcialmente um estado")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Estado atualizado"),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    void atualizarParcial(Long id, Map<String, Object> valores);

    @ApiOperation("Exclui um estado")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Estado excluido"),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    void remover(Long id);
}