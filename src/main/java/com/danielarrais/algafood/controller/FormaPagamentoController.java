package com.danielarrais.algafood.controller;

import com.danielarrais.algafood.domain.model.FormaPagamento;
import com.danielarrais.algafood.domain.service.FormaPagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {
    private final FormaPagamentoService formaPagamentoService;

    public FormaPagamentoController(FormaPagamentoService formaPagamentoService) {
        this.formaPagamentoService = formaPagamentoService;
    }

    @GetMapping()
    public List<FormaPagamento> listar() {
        return formaPagamentoService.listar();
    }

    @GetMapping("/{id}")
    public FormaPagamento buscar(@PathVariable Long id) {
        return formaPagamentoService.buscarObrigatorio(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody FormaPagamento formaPagamento) {
        formaPagamentoService.salvar(formaPagamento);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizar(@PathVariable Long id, @RequestBody FormaPagamento formaPagamento) {
        formaPagamentoService.atualizar(id, formaPagamento);
    }

    @PatchMapping("/{id}")
    public void atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> valores) {
        formaPagamentoService.atualizar(id, valores);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        formaPagamentoService.remover(id);
    }
}