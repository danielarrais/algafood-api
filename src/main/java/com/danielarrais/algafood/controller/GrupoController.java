package com.danielarrais.algafood.controller;

import com.danielarrais.algafood.domain.exception.EntidadeEmUsoException;
import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Grupo;
import com.danielarrais.algafood.domain.service.GrupoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Grupo> buscar(@PathVariable Long id) {
        return grupoService.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody Grupo grupo) {
        grupoService.salvar(grupo);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Grupo grupo) {
        try {
            grupoService.atualizar(id, grupo);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> valores) {
        try {
            grupoService.atualizar(id, valores);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            grupoService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeEmUsoException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}