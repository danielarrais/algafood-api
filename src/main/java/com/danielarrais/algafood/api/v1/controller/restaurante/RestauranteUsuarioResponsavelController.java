package com.danielarrais.algafood.api.v1.controller.restaurante;

import com.danielarrais.algafood.api.v1.assembler.usuario.UsuarioSimpleOutputAssembler;
import com.danielarrais.algafood.api.v1.dto.output.usuario.UsuarioSimpleOutput;
import org.springframework.http.MediaType;
import com.danielarrais.algafood.domain.service.RestauranteService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOAS {
    private final RestauranteService restauranteService;
    private final UsuarioSimpleOutputAssembler usuarioSimpleOutputAssembler;

    public RestauranteUsuarioResponsavelController(RestauranteService restauranteService, UsuarioSimpleOutputAssembler usuarioSimpleOutputAssembler) {
        this.restauranteService = restauranteService;
        this.usuarioSimpleOutputAssembler = usuarioSimpleOutputAssembler;
    }

    @GetMapping()
    public CollectionModel<UsuarioSimpleOutput> listar(@PathVariable Long restauranteId) {
        var restaurante = restauranteService.buscarObrigatorio(restauranteId);
        return usuarioSimpleOutputAssembler.toCollectionModel(restauranteId, restaurante.getResponsaveis());
    }

    @PutMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long idUsuario) {
        restauranteService.associarResponsavel(restauranteId, idUsuario);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long idUsuario) {
        restauranteService.desassociarResponsavel(restauranteId, idUsuario);

        return ResponseEntity.noContent().build();
    }
}