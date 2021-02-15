package com.danielarrais.algafood.api.controller.formaPagamento;

import com.danielarrais.algafood.api.dto.input.formaPagamento.FormaPagamentoInput;
import com.danielarrais.algafood.api.dto.output.formaPagamento.FormaPagamentoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Api(tags = "Formas de Pagamento")
interface FormaPagamentoControllerOAS {

    ResponseEntity<?> listar(Pageable pageable);

    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    FormaPagamentoOutput buscar(Long id);

    @ApiResponses({
            @ApiResponse(code = 201, message = "Forma de pagamento cadastrada", response = Problem.class)
    })
    void adicionar(FormaPagamentoInput formaPagamentoInput);
    
    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento atualizada"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    void atualizar(Long id, FormaPagamentoInput formaPagamentoInput);
    
    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento atualizada"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    void atualizarParcial(Long id, Map<String, Object> valores);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento excluida"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    void remover(Long id);
}