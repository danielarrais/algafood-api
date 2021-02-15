package com.danielarrais.algafood.api.controller.restaurante;

import com.danielarrais.algafood.api.dto.output.formaPagamento.FormaPagamentoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Formas de Pagamento do Restaurante")
interface RestauranteFormaPagamentoControllerOAS {
    List<FormaPagamentoOutput> listar(@ApiParam("ID do restaurante") Long restauranteId);

    @ApiOperation("Associa forma de pagamento a um restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento associada ao restaurante"),
            @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrada", response = Problem.class)
    })
    void associar(@ApiParam("ID do restaurante") Long restauranteId, @ApiParam("ID da forma de pagamento") Long idFormaPagamento);

    @ApiOperation("Desassocia forma de pagamento a um restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento desassociada ao restaurante"),
            @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = Problem.class)
    })
    void desassociar(@ApiParam("ID do restaurante") Long restauranteId, @ApiParam("ID da forma de pagamento") Long idFormaPagamento);
}