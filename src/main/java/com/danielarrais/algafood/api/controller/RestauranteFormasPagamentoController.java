package com.danielarrais.algafood.api.controller;

import com.danielarrais.algafood.api.dto.output.formaPagamento.FormaPagamentoOutput;
import com.danielarrais.algafood.domain.service.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormasPagamentoController {
    private final RestauranteService restauranteService;

    public RestauranteFormasPagamentoController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping()
    public List<FormaPagamentoOutput> listar(@PathVariable Long restauranteId) {
        var restaurante = restauranteService.buscarObrigatorio(restauranteId);
        return mapper(restaurante.getFormasPagamento(), FormaPagamentoOutput.class);
    }

    @PutMapping("/{idFormaPagamento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionar(@PathVariable Long restauranteId, @PathVariable Long idFormaPagamento) {
        restauranteService.adicionarFormaPagamento(restauranteId, idFormaPagamento);
    }

    @DeleteMapping("/{idFormaPagamento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long restauranteId, @PathVariable Long idFormaPagamento) {
        restauranteService.removerFormaPagamento(restauranteId, idFormaPagamento);
    }
}