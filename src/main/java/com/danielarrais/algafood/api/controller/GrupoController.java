package com.danielarrais.algafood.api.controller;

import com.danielarrais.algafood.domain.model.Grupo;
import com.danielarrais.algafood.domain.service.GrupoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
    private final GrupoService grupoService;

    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @GetMapping()
    public List<Grupo> listar() {
        return grupoService.listar();
    }

    @GetMapping("/{id}")
    public Grupo buscar(@PathVariable Long id) {
        return grupoService.buscarObrigatorio(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody @Valid Grupo grupo) {
        grupoService.salvar(grupo);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid Grupo grupo) {
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