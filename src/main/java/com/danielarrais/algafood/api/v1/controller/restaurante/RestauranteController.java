package com.danielarrais.algafood.api.v1.controller.restaurante;

import com.danielarrais.algafood.api.v1.assembler.restaurante.RestauranteFullOutputAssembler;
import com.danielarrais.algafood.api.v1.assembler.restaurante.RestauranteSimpleOutputAssembler;
import com.danielarrais.algafood.api.v1.dto.input.restaurante.RestauranteInput;
import com.danielarrais.algafood.api.v1.dto.output.restaurante.RestauranteFullOutput;
import com.danielarrais.algafood.api.v1.dto.output.restaurante.RestauranteSimpleOutput;
import org.springframework.http.MediaType;
import com.danielarrais.algafood.domain.exception.DependenciaNaoEncontradaException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.service.RestauranteService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping(path = "/v1/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOAS {
    private final RestauranteService restauranteService;
    private final RestauranteSimpleOutputAssembler restauranteSimpleOutputAssembler;
    private final RestauranteFullOutputAssembler restauranteFullOutputAssembler;
    private final PagedResourcesAssembler<Restaurante> pagedResourcesAssembler;

    public RestauranteController(RestauranteService restauranteService,
                                 RestauranteSimpleOutputAssembler restauranteSimpleOutputAssembler,
                                 RestauranteFullOutputAssembler restauranteFullOutputAssembler,
                                 PagedResourcesAssembler<Restaurante> pagedResourcesAssembler) {
        this.restauranteService = restauranteService;
        this.restauranteSimpleOutputAssembler = restauranteSimpleOutputAssembler;
        this.restauranteFullOutputAssembler = restauranteFullOutputAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping()
    public PagedModel<RestauranteSimpleOutput> listar(Pageable pageable) {
        var restaurantes = restauranteService.listar(pageable);
        return pagedResourcesAssembler.toModel(restaurantes, restauranteSimpleOutputAssembler);
    }

    @GetMapping("/{id}")
    public RestauranteFullOutput buscar(@PathVariable Long id) {
        var restaurante = restauranteService.buscarObrigatorio(id);
        return restauranteFullOutputAssembler.toModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        var restaurante = mapper(restauranteInput, Restaurante.class);
        restauranteService.salvar(restaurante);
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteInput restauranteInput) {
        var restaurante = mapper(restauranteInput, Restaurante.class);
        restauranteService.atualizar(id, restaurante);
    }

    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        restauranteService.ativar(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/inativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        restauranteService.inativar(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/ativos")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarBach(@RequestBody List<Long> ids) {
        try {
            restauranteService.ativar(ids);
        } catch (RegistroNaoEncontradoException e) {
            throw new DependenciaNaoEncontradaException(e.getMessage(), e);
        }
    }

    @PutMapping("/inativos")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarBach(@RequestBody List<Long> ids) {
        try {
            restauranteService.inativar(ids);
        } catch (RegistroNaoEncontradoException e) {
            throw new DependenciaNaoEncontradaException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}/aberto")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> abrir(@PathVariable Long id) {
        restauranteService.abrir(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/fechado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> fechar(@PathVariable Long id) {
        restauranteService.fechar(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        restauranteService.remover(id);
    }
}