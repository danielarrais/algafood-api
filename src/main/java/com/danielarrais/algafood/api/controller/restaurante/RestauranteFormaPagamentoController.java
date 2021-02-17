package com.danielarrais.algafood.api.controller.restaurante;

import com.danielarrais.algafood.api.assembler.formaPagamento.FormaPagamentoOutputAssembler;
import com.danielarrais.algafood.api.dto.output.formaPagamento.FormaPagamentoOutput;
import com.danielarrais.algafood.core.hateoas.LinkBuilder;
import com.danielarrais.algafood.domain.service.RestauranteService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOAS {
    private final RestauranteService restauranteService;
    private final FormaPagamentoOutputAssembler formaPagamentoOutputAssembler;

    public RestauranteFormaPagamentoController(RestauranteService restauranteService, FormaPagamentoOutputAssembler formaPagamentoOutputAssembler) {
        this.restauranteService = restauranteService;
        this.formaPagamentoOutputAssembler = formaPagamentoOutputAssembler;
    }

    @GetMapping()
    public CollectionModel<FormaPagamentoOutput> listar(@PathVariable Long restauranteId) {
        var restaurante = restauranteService.buscarObrigatorio(restauranteId);
        return formaPagamentoOutputAssembler
                .toCollectionModel(restaurante.getFormasPagamento()).removeLinks()
                .add(LinkBuilder.linkFormasPagamentoRestaurante(restauranteId));
    }

    @PutMapping("/{idFormaPagamento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long idFormaPagamento) {
        restauranteService.associarFormaPagamento(restauranteId, idFormaPagamento);
    }

    @DeleteMapping("/{idFormaPagamento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long idFormaPagamento) {
        restauranteService.desassociarFormaPagamento(restauranteId, idFormaPagamento);
    }
}