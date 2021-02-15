package com.danielarrais.algafood.api.controller.restaurante;

import com.danielarrais.algafood.api.dto.input.restaurante.ProdutoFotoInput;
import com.danielarrais.algafood.api.dto.output.restaurante.FotoProdutoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

@Api(tags = "Produtos")
interface RestauranteFotoProdutoControllerOAS {

    @ApiOperation("Atualiza foto do produto")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Foto do produto atualizada", response = Problem.class),
            @ApiResponse(code = 404, message = "Foto do produto não encontrada", response = Problem.class)
    })
    void atualizarFoto(@ApiParam("ID do restaurante") Long restauranteId,
                       @ApiParam("ID do produto") Long produtoId,
                       @ApiParam("Corpo") ProdutoFotoInput produtoFotoInput);

    @ApiOperation("Busca foto do produto")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Foto do produto encontrada", response = Problem.class),
            @ApiResponse(code = 404, message = "Foto do produto não encontrada", response = Problem.class)
    })
    FotoProdutoOutput buscar(@ApiParam("ID do restaurante") Long restauranteId,
                             @ApiParam("ID do produto") Long produtoId);

    @ApiOperation("Baixa foto do produto")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Foto do produto encontrada", response = Problem.class),
            @ApiResponse(code = 404, message = "Foto do produto não encontrada", response = Problem.class)
    })
    ResponseEntity<?> downloadFoto(@ApiParam("ID do restaurante") Long restauranteId,
                                   @ApiParam("ID do produto") Long produtoId,
                                   @ApiParam(hidden = true) String mediaTypeName);

    @ApiOperation("Remove foto do produto")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Foto do produto excluída"),
            @ApiResponse(code = 404, message = "Foto do produto não encontrada", response = Problem.class)
    })
    void deletar(@ApiParam("ID do restaurante") Long restauranteId,
                 @ApiParam("ID do produto") Long produtoId);
}