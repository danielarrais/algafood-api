package com.danielarrais.algafood.api.controller.pedido;

import com.danielarrais.algafood.api.dto.input.pedido.PedidoInput;
import com.danielarrais.algafood.api.dto.output.pedido.PedidoFullOutput;
import com.danielarrais.algafood.api.dto.output.pedido.PedidoSimpleOutput;
import com.danielarrais.algafood.api.exception.Problem;
import com.danielarrais.algafood.domain.filter.PedidoFilter;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Pedidos")
@RestController
@RequestMapping(path = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
interface PedidoControllerOAS {

    @ApiOperation("Adiciona pedido")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Pedido cadastrado", response = Problem.class)
    })
    void adicionar(@ApiParam("Corpo") PedidoInput pedidoInput);

    @ApiOperation("Lista pedidos")
    Page<PedidoSimpleOutput> listar(@ApiParam("Filtros") PedidoFilter filtro, Pageable pageable);

    @ApiOperation("Busca pedido")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    PedidoFullOutput buscar(@ApiParam("Código do pedido") String codigo);
}