package com.danielarrais.algafood.api.v1.controller.restaurante;

import com.danielarrais.algafood.api.v1.dto.input.restaurante.ProdutoFotoInput;
import com.danielarrais.algafood.api.v1.dto.output.restaurante.FotoProdutoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "Produtos")
interface RestauranteFotoProdutoControllerOAS {

    @ApiOperation("Atualiza foto do produto")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Foto do produto atualizada", response = Problem.class),
            @ApiResponse(code = 404, message = "Foto do produto não encontrada", response = Problem.class)
    })
    void atualizarFoto(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
                       @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId,
                       @ApiParam(value = "Corpo") ProdutoFotoInput produtoFotoInput,
                       @ApiParam(value = "Arquivo da foto (Tamanho máximo de 500KB)", required = true) MultipartFile arquivo);

    @ApiOperation("Busca foto do produto")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Foto do produto encontrada", response = Problem.class),
            @ApiResponse(code = 404, message = "Foto do produto não encontrada", response = Problem.class)
    })
    FotoProdutoOutput buscar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
                             @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId);

    @ApiOperation("Baixa foto do produto")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Foto do produto encontrada", response = Problem.class),
            @ApiResponse(code = 404, message = "Foto do produto não encontrada", response = Problem.class)
    })
    ResponseEntity<?> downloadFoto(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
                                   @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId,
                                   @ApiParam(hidden = true) String mediaTypeName);

    @ApiOperation("Remove foto do produto")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Foto do produto excluída"),
            @ApiResponse(code = 404, message = "Foto do produto não encontrada", response = Problem.class)
    })
    void deletar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
                 @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId);
}