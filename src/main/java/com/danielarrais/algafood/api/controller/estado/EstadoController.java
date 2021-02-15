package com.danielarrais.algafood.api.controller.estado;

import com.danielarrais.algafood.api.dto.input.estado.EstadoInput;
import com.danielarrais.algafood.api.dto.output.estado.EstadoOutput;
import com.danielarrais.algafood.domain.model.Estado;
import com.danielarrais.algafood.domain.service.EstadoService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@Api(tags = "Estados")
@RestController
@RequestMapping("/estados")
public class EstadoController implements EstadoControllerOAS {
    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping()
    public Page<EstadoOutput> listar(Pageable pageable) {
        var estados = estadoService.listar(pageable);
        return mapper(estados, EstadoOutput.class);
    }

    @GetMapping("/{id}")
    public EstadoOutput buscar(@PathVariable Long id) {
        var estado = estadoService.buscarObrigatorio(id);
        return mapper(estado, EstadoOutput.class);
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

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizarParcial(@PathVariable Long id, @RequestBody @Valid Map<String, Object> valores) {
        estadoService.atualizar(id, valores);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        estadoService.remover(id);
    }
}