package com.danielarrais.algafood.api.v1.controller.pedido;

import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

@Api(tags = "Alteração de Status de Pedido")
interface FluxoPedidoControllerOAS {

    @ApiOperation("Cancela pedido")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> cancelar(@ApiParam("Código do pedido") String codigo);

    @ApiOperation("Confirma pedido")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> confirmar(@ApiParam("Código do pedido") String codigo);

    @ApiOperation("Entrega pedido")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> entregar(@ApiParam("Código do pedido") String codigo);
}