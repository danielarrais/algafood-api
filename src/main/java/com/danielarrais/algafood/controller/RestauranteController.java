package com.danielarrais.algafood.controller;

import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.service.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping()
    public List<Restaurante> listar() {
        return restauranteService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        return restauranteService.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            restauranteService.salvar(restaurante);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        try {
            restauranteService.atualizar(id, restaurante);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException exception) {
            return exception.isDependencia() ?
                    ResponseEntity.badRequest().body(exception.getMessage()) :
                    ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> valores) {
        try {
            restauranteService.atualizar(id, valores);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException exception) {
            return exception.isDependencia() ?
                    ResponseEntity.badRequest().body(exception.getMessage()) :
                    ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            restauranteService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}