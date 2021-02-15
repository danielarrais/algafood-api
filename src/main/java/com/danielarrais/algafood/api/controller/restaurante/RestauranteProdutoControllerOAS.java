package com.danielarrais.algafood.api.controller.restaurante;

import com.danielarrais.algafood.api.dto.input.restaurante.ProdutoInput;
import com.danielarrais.algafood.api.dto.output.restaurante.ProdutoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(tags = "Produtos")
interface RestauranteProdutoControllerOAS {

    @ApiOperation("Lista produtos de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    List<ProdutoOutput> listar(Long restauranteId);

    @ApiOperation("Adiciona produto a um restaurante")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Produto cadastrado"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void adicionar(Long restauranteId, ProdutoInput produtoInput);

    @ApiOperation("Busca produto de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Restaurante encontrado"),
            @ApiResponse(code = 400, message = "ID do restaurante ou do produto inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ProdutoOutput buscar(Long id, Long restauranteId);

    @ApiOperation("Atualiza produto de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Produto atualizado"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void atualizar(Long id, Long restauranteId, ProdutoInput produtoInput);
}