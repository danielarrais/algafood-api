package com.danielarrais.algafood.controller;

import com.danielarrais.algafood.domain.model.Permissao;
import com.danielarrais.algafood.domain.service.PermissaoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {
    private final PermissaoService permissaoService;

    public PermissaoController(PermissaoService permissaoService) {
        this.permissaoService = permissaoService;
    }

    @GetMapping()
    public List<Permissao> listar() {
        return permissaoService.listar();
    }

    @GetMapping("/{id}")
    public Permissao buscar(@PathVariable Long id) {
        return permissaoService.buscarObrigatorio(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody Permissao permissao) {
        permissaoService.salvar(permissao);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizar(@PathVariable Long id, @RequestBody Permissao permissao) {
        permissaoService.atualizar(id, permissao);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> valores) {
        permissaoService.atualizar(id, valores);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        permissaoService.remover(id);
    }
}