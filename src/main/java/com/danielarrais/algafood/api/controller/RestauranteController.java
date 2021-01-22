package com.danielarrais.algafood.api.controller;

import com.danielarrais.algafood.api.dto.input.restaurante.RestauranteInput;
import com.danielarrais.algafood.api.dto.output.restaurante.RestauranteOutput;
import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.service.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.danielarrais.algafood.util.ControllerUtils.mapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping()
    public List<RestauranteOutput> listar() {
        var restaurantes = restauranteService.listar();
        return mapper(restaurantes, RestauranteOutput.class);
    }

    @GetMapping("/{id}")
    public RestauranteOutput buscar(@PathVariable Long id) {
        var restaurante = restauranteService.buscarObrigatorio(id);
        return mapper(restaurante, RestauranteOutput.class);
    }

    @PostMapping
    public void adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        var restaurante = mapper(restauranteInput, Restaurante.class);
        restauranteService.salvar(restaurante);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteInput restauranteInput) {
        var restaurante = mapper(restauranteInput, Restaurante.class);
        restauranteService.atualizar(id, restaurante);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizarParcial(@PathVariable Long id, @RequestBody @Valid Map<String, Object> valores) {
        restauranteService.atualizar(id, valores);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        restauranteService.remover(id);
    }
}