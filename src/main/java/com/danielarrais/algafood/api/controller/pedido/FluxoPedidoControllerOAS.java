package com.danielarrais.algafood.api.controller.pedido;

import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;

@Api(tags = "Alteração de Status de Pedido")
interface FluxoPedidoControllerOAS {

    @ApiOperation("Cancela pedido")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    void cancelar(@ApiParam("Código do pedido") String codigo);

    @ApiOperation("Confirma pedido")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    void confirmar(@ApiParam("Código do pedido") String codigo);

    @ApiOperation("Entrega pedido")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    void entregar(@ApiParam("Código do pedido") String codigo);
}