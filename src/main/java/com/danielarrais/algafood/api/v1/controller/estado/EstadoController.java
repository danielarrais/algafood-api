package com.danielarrais.algafood.api.v1.controller.estado;

import com.danielarrais.algafood.api.v1.assembler.estado.EstadoOutputAssembler;
import com.danielarrais.algafood.api.v1.dto.input.estado.EstadoInput;
import com.danielarrais.algafood.api.v1.dto.output.estado.EstadoOutput;
import com.danielarrais.algafood.domain.model.Estado;
import com.danielarrais.algafood.domain.service.EstadoService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@Api(tags = "Estados")
@RestController
@RequestMapping(path = "/v1/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOAS {
    private final EstadoService estadoService;
    private final EstadoOutputAssembler estadoOutputAssembler;
    private final PagedResourcesAssembler<Estado> pagedResourcesAssembler;

    public EstadoController(EstadoService estadoService, EstadoOutputAssembler estadoOutputAssembler, PagedResourcesAssembler<Estado> pagedResourcesAssembler) {
        this.estadoService = estadoService;
        this.estadoOutputAssembler = estadoOutputAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping()
    public PagedModel<EstadoOutput> listar(Pageable pageable) {
        var estados = estadoService.listar(pageable);
        return pagedResourcesAssembler.toModel(estados,estadoOutputAssembler);
    }

    @GetMapping("/{id}")
    public EstadoOutput buscar(@PathVariable Long id) {
        var estado = estadoService.buscarObrigatorio(id);
        return estadoOutputAssembler.toModel(estado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        var estado = mapper(estadoInput, Estado.class);
        estadoService.salvar(estado);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid EstadoInput estadoInput) {
        var estado = mapper(estadoInput, Estado.class);
        estadoService.atualizar(id, estado);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        estadoService.remover(id);
    }
}