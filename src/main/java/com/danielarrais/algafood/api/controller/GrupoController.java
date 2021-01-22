package com.danielarrais.algafood.api.controller;

import com.danielarrais.algafood.api.dto.input.grupo.GrupoInput;
import com.danielarrais.algafood.api.dto.output.grupo.GrupoOutput;
import com.danielarrais.algafood.domain.model.Grupo;
import com.danielarrais.algafood.domain.service.GrupoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.danielarrais.algafood.util.ControllerUtils.mapper;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
    private final GrupoService grupoService;

    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @GetMapping()
    public List<GrupoOutput> listar() {
        var grupos = grupoService.listar();
        return mapper(grupos, GrupoOutput.class);
    }

    @GetMapping("/{id}")
    public GrupoOutput buscar(@PathVariable Long id) {
        var grupo = grupoService.buscarObrigatorio(id);
        return mapper(grupo, GrupoOutput.class);
    }

    @PostMapping
    public void adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        var grupo = mapper(grupoInput, Grupo.class);
        grupoService.salvar(grupo);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid GrupoInput grupoInput) {
        var grupo = mapper(grupoInput, Grupo.class);
        grupoService.atualizar(id, grupo);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizarParcial(@PathVariable Long id, @RequestBody @Valid Map<String, Object> valores) {
        grupoService.atualizar(id, valores);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        grupoService.remover(id);
    }
}