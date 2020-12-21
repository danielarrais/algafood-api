package com.danielarrais.algafood.controller;

import com.danielarrais.algafood.domain.exception.EntidadeEmUsoException;
import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Usuario;
import com.danielarrais.algafood.domain.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        return usuarioService.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody Usuario usuario) {
        usuarioService.salvar(usuario);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            usuarioService.atualizar(id, usuario);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> valores) {
        try {
            usuarioService.atualizar(id, valores);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            usuarioService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeEmUsoException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}