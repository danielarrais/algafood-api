package com.danielarrais.algafood.api.controller.restaurante;

import com.danielarrais.algafood.api.dto.output.formaPagamento.FormaPagamentoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Formas de Pagamento do Restaurante")
interface RestauranteFormaPagamentoControllerOAS {
    CollectionModel<FormaPagamentoOutput> listar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Associa forma de pagamento a um restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento associada ao restaurante"),
            @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrada", response = Problem.class)
    })
    ResponseEntity<Void> associar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
                  @ApiParam(value = "ID da forma de pagamento", example = "1", required = true) Long idFormaPagamento);

    @ApiOperation("Desassocia forma de pagamento a um restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento desassociada ao restaurante"),
            @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> desassociar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
                     @ApiParam(value = "ID da forma de pagamento", example = "1", required = true) Long idFormaPagamento);
}