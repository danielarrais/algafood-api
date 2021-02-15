package com.danielarrais.algafood.api.controller.restaurante;

import com.danielarrais.algafood.api.dto.output.formaPagamento.FormaPagamentoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(tags = "Formas de Pagamento do Restaurante")
interface RestauranteFormaPagamentoControllerOAS {
    List<FormaPagamentoOutput> listar(Long restauranteId);

    @ApiOperation("Associa forma de pagamento a um restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento associada ao restaurante"),
            @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrada", response = Problem.class)
    })
    void associar(Long restauranteId, Long idFormaPagamento);

    @ApiOperation("Desassocia forma de pagamento a um restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento desassociada ao restaurante"),
            @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = Problem.class)
    })
    void desassociar(Long restauranteId, Long idFormaPagamento);
}