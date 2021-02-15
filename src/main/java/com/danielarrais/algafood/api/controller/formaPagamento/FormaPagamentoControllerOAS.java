package com.danielarrais.algafood.api.controller.formaPagamento;

import com.danielarrais.algafood.api.dto.input.formaPagamento.FormaPagamentoInput;
import com.danielarrais.algafood.api.dto.output.formaPagamento.FormaPagamentoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Api(tags = "Formas de Pagamento")
interface FormaPagamentoControllerOAS {

    @ApiOperation("Lista formas de pagamentos")
    Page<FormaPagamentoOutput> listar(Pageable pageable);

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