package com.danielarrais.algafood.api.v1.controller.formaPagamento;

import com.danielarrais.algafood.api.exception.Problem;
import com.danielarrais.algafood.api.v1.dto.input.formaPagamento.FormaPagamentoInput;
import com.danielarrais.algafood.api.v1.dto.output.formaPagamento.FormaPagamentoOutput;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Formas de Pagamento")
interface FormaPagamentoControllerOAS {

    @ApiOperation("Lista formas de pagamentos")
    PagedModel<FormaPagamentoOutput> listar(Pageable pageable);

    @ApiOperation("Busca forma de pagamento")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    FormaPagamentoOutput buscar(@ApiParam(value = "ID da forma de pagamento", example = "1", required = true) Long id);

    @ApiOperation("Adiciona forma de pagamento")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Forma de pagamento cadastrada", response = Problem.class)
    })
    void adicionar(@ApiParam("Corpo") FormaPagamentoInput formaPagamentoInput);

    @ApiOperation("Atualiza forma de pagamento")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento atualizada"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    void atualizar(@ApiParam(value = "ID da forma de pagamento", example = "1", required = true) Long id, @ApiParam("Corpo") FormaPagamentoInput formaPagamentoInput);

    @ApiOperation("Exclui forma de pagamento")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento excluida"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID da forma de pagamento", example = "1", required = true) Long id);
}