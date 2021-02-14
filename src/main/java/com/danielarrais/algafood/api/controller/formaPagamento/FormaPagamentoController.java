package com.danielarrais.algafood.api.controller.formaPagamento;

import com.danielarrais.algafood.api.dto.input.formaPagamento.FormaPagamentoInput;
import com.danielarrais.algafood.api.dto.output.formaPagamento.FormaPagamentoOutput;
import com.danielarrais.algafood.domain.model.FormaPagamento;
import com.danielarrais.algafood.domain.service.FormaPagamentoService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@Api(tags = "Formas de Pagamento")
@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    private final FormaPagamentoService formaPagamentoService;

    public FormaPagamentoController(FormaPagamentoService formaPagamentoService) {
        this.formaPagamentoService = formaPagamentoService;
    }

    @GetMapping()
    public ResponseEntity<?> listar(Pageable pageable) {
        var formaPagamentos = formaPagamentoService.listar(pageable);
        var formasPagamentoDTO = mapper(formaPagamentos, FormaPagamentoOutput.class);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.noCache())
                .body(formasPagamentoDTO);
    }

    @GetMapping("/{id}")
    public FormaPagamentoOutput buscar(@PathVariable Long id) {
        var formaPagamento = formaPagamentoService.buscarObrigatorio(id);
        return mapper(formaPagamento, FormaPagamentoOutput.class);
    }

    @PostMapping
    public void adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        var formaPagamento = mapper(formaPagamentoInput, FormaPagamento.class);
        formaPagamentoService.salvar(formaPagamento);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        var formaPagamento = mapper(formaPagamentoInput, FormaPagamento.class);
        formaPagamentoService.atualizar(id, formaPagamento);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizarParcial(@PathVariable Long id, @RequestBody @Valid Map<String, Object> valores) {
        formaPagamentoService.atualizar(id, valores);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        formaPagamentoService.remover(id);
    }
}