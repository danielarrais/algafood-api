package com.danielarrais.algafood.api.v1.controller.restaurante;

import com.danielarrais.algafood.api.v1.assembler.formaPagamento.FormaPagamentoOutputAssembler;
import com.danielarrais.algafood.api.v1.dto.output.formaPagamento.FormaPagamentoOutput;
import com.danielarrais.algafood.core.util.MediaTypes;
import com.danielarrais.algafood.domain.service.RestauranteService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/formas-pagamento", produces = MediaTypes.JSON_ALGAFOOD_V1)
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
                .toCollectionModel(restauranteId, restaurante.getFormasPagamento());
    }

    @PutMapping("/{idFormaPagamento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long idFormaPagamento) {
        restauranteService.associarFormaPagamento(restauranteId, idFormaPagamento);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idFormaPagamento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long idFormaPagamento) {
        restauranteService.desassociarFormaPagamento(restauranteId, idFormaPagamento);

        return ResponseEntity.noContent().build();
    }
}