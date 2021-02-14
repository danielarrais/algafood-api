package com.danielarrais.algafood.api.controller.restaurante;

import com.danielarrais.algafood.api.dto.output.formaPagamento.FormaPagamentoOutput;
import com.danielarrais.algafood.domain.service.RestauranteService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@Api(tags = "Formas de Pagamento do Restaurante")
@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {
    private final RestauranteService restauranteService;

    public RestauranteFormaPagamentoController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping()
    public List<FormaPagamentoOutput> listar(@PathVariable Long restauranteId) {
        var restaurante = restauranteService.buscarObrigatorio(restauranteId);
        return mapper(restaurante.getFormasPagamento(), FormaPagamentoOutput.class);
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