package com.danielarrais.algafood.api.v1.controller.formaPagamento;

import com.danielarrais.algafood.api.exception.Problem;
import com.danielarrais.algafood.api.v1.assembler.formaPagamento.FormaPagamentoOutputAssembler;
import com.danielarrais.algafood.api.v1.dto.input.formaPagamento.FormaPagamentoInput;
import com.danielarrais.algafood.api.v1.dto.output.formaPagamento.FormaPagamentoOutput;
import com.danielarrais.algafood.domain.model.FormaPagamento;
import com.danielarrais.algafood.domain.service.FormaPagamentoService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping(path = "/v1/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormaPagamentoControllerOAS {

    private final FormaPagamentoService formaPagamentoService;
    private final FormaPagamentoOutputAssembler formaPagamentoOutputAssembler;
    private final PagedResourcesAssembler<FormaPagamento> pagedResourcesAssembler;

    public FormaPagamentoController(FormaPagamentoService formaPagamentoService,
                                    FormaPagamentoOutputAssembler formaPagamentoOutputAssembler,
                                    PagedResourcesAssembler<FormaPagamento> pagedResourcesAssembler) {
        this.formaPagamentoService = formaPagamentoService;
        this.formaPagamentoOutputAssembler = formaPagamentoOutputAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping()
    public PagedModel<FormaPagamentoOutput> listar(Pageable pageable) {
        var formaPagamentos = formaPagamentoService.listar(pageable);
        return pagedResourcesAssembler.toModel(formaPagamentos, formaPagamentoOutputAssembler);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    public FormaPagamentoOutput buscar(@PathVariable Long id) {
        var formaPagamento = formaPagamentoService.buscarObrigatorio(id);
        return formaPagamentoOutputAssembler.toModel(formaPagamento);
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