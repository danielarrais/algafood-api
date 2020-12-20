package com.danielarrais.algafood.controller;

import com.danielarrais.algafood.domain.exception.EntidadeEmUsoException;
import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Produto;
import com.danielarrais.algafood.domain.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping()
    public List<Produto> listar() {
        return produtoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        return produtoService.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionar(@RequestBody Produto produto) {
        try {
            produtoService.salvar(produto);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        try {
            produtoService.atualizar(id, produto);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException exception) {
            return exception.isDependencia() ?
                    ResponseEntity.badRequest().body(exception.getMessage()) :
                    ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> valores) {
        try {
            produtoService.atualizar(id, valores);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException exception) {
            return exception.isDependencia() ?
                    ResponseEntity.badRequest().body(exception.getMessage()) :
                    ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            produtoService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeEmUsoException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}