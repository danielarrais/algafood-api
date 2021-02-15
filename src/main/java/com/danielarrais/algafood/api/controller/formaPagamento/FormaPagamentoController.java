package com.danielarrais.algafood.api.controller.formaPagamento;

import com.danielarrais.algafood.api.dto.input.formaPagamento.FormaPagamentoInput;
import com.danielarrais.algafood.api.dto.output.formaPagamento.FormaPagamentoOutput;
import com.danielarrais.algafood.api.exception.Problem;
import com.danielarrais.algafood.domain.model.FormaPagamento;
import com.danielarrais.algafood.domain.service.FormaPagamentoService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping(path = "/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormaPagamentoControllerOAS{

    private final FormaPagamentoService formaPagamentoService;

    public FormaPagamentoController(FormaPagamentoService formaPagamentoService) {
        this.formaPagamentoService = formaPagamentoService;
    }

    @GetMapping()
    public Page<FormaPagamentoOutput> listar(Pageable pageable) {
        var formaPagamentos = formaPagamentoService.listar(pageable);
        return mapper(formaPagamentos, FormaPagamentoOutput.class);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    public FormaPagamentoOutput buscar(@PathVariable Long id) {
        var formaPagamento = formaPagamentoService.buscarObrigatorio(id);
        return mapper(formaPagamento, FormaPagamentoOutput.class);
    }

    @PostMapping
    @ApiResponses({
            @ApiResponse(code = 201, message = "Forma de pagamento cadastrada", response = Problem.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        var formaPagamento = mapper(formaPagamentoInput, FormaPagamento.class);
        formaPagamentoService.salvar(formaPagamento);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento atualizada"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    public void atualizar(@PathVariable Long id, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        var formaPagamento = mapper(formaPagamentoInput, FormaPagamento.class);
        formaPagamentoService.atualizar(id, formaPagamento);
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento excluida"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    public void remover(@PathVariable Long id) {
        formaPagamentoService.remover(id);
    }
}