package com.danielarrais.algafood.api.controller;

import com.danielarrais.algafood.domain.model.Usuario;
import com.danielarrais.algafood.domain.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable Long id) {
        return usuarioService.buscarObrigatorio(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody @Valid Usuario usuario) {
        usuarioService.salvar(usuario);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
        usuarioService.atualizar(id, usuario);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizarParcial(@PathVariable Long id, @RequestBody @Valid Map<String, Object> valores) {
        usuarioService.atualizar(id, valores);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        usuarioService.remover(id);
    }
}