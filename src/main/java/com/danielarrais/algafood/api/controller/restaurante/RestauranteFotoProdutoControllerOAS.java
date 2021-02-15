package com.danielarrais.algafood.api.controller.restaurante;

import com.danielarrais.algafood.api.dto.input.restaurante.ProdutoFotoInput;
import com.danielarrais.algafood.api.dto.output.restaurante.FotoProdutoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

@Api(tags = "Produtos")
interface RestauranteFotoProdutoControllerOAS {

    @ApiResponses({
            @ApiResponse(code = 200, message = "Foto do produto atualizada", response = Problem.class),
            @ApiResponse(code = 404, message = "Foto do produto não encontrada", response = Problem.class)
    })
    void atualizarFoto(Long restauranteId,
                       Long produtoId,
                       ProdutoFotoInput produtoFotoInput);

    @ApiResponses({
            @ApiResponse(code = 200, message = "Foto do produto encontrada", response = Problem.class),
            @ApiResponse(code = 404, message = "Foto do produto não encontrada", response = Problem.class)
    })
    FotoProdutoOutput buscar(Long restauranteId,
                             Long produtoId);

    @ApiResponses({
            @ApiResponse(code = 200, message = "Foto do produto encontrada", response = Problem.class),
            @ApiResponse(code = 404, message = "Foto do produto não encontrada", response = Problem.class)
    })
    ResponseEntity<?> downloadFoto(Long restauranteId,
                                   Long produtoId,
                                   String mediaTypeName);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Foto do produto excluída"),
            @ApiResponse(code = 404, message = "Foto do produto não encontrada", response = Problem.class)
    })
    void deletar(Long restauranteId,
                 Long produtoId);
}