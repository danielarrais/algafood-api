package com.danielarrais.algafood.api.controller.restaurante;

import com.danielarrais.algafood.api.dto.output.usuario.UsuarioSimpleOutput;
import com.danielarrais.algafood.domain.service.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOAS{
    private final RestauranteService restauranteService;

    public RestauranteUsuarioResponsavelController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping()
    public List<UsuarioSimpleOutput> listar(@PathVariable Long restauranteId) {
        var restaurante = restauranteService.buscarObrigatorio(restauranteId);
        return mapper(restaurante.getResponsaveis(), UsuarioSimpleOutput.class);
    }

    @PutMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long idUsuario) {
        restauranteService.associarResponsavel(restauranteId, idUsuario);
    }

    @DeleteMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long idUsuario) {
        restauranteService.desassociarResponsavel(restauranteId, idUsuario);
    }
}