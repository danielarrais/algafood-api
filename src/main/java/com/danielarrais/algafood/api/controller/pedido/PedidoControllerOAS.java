package com.danielarrais.algafood.api.controller.pedido;

import com.danielarrais.algafood.api.dto.input.pedido.PedidoInput;
import com.danielarrais.algafood.api.dto.output.pedido.PedidoFullOutput;
import com.danielarrais.algafood.api.dto.output.pedido.PedidoSimpleOutput;
import com.danielarrais.algafood.api.exception.Problem;
import com.danielarrais.algafood.domain.filter.PedidoFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "Pedidos")
@RestController
@RequestMapping(path = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
interface PedidoControllerOAS {

    @ApiOperation("Adiciona pedido")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Pedido cadastrado", response = Problem.class)
    })
    void adicionar(@RequestBody @Valid PedidoInput pedidoInput);

    @ApiOperation("Lista pedidos")
    Page<PedidoSimpleOutput> listar(PedidoFilter filtro, Pageable pageable);

    @ApiOperation("Busca pedido")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    PedidoFullOutput buscar(@PathVariable String codigo);
}