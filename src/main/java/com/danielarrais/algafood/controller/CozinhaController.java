package com.danielarrais.algafood.controller;

import com.danielarrais.algafood.domain.model.Cozinha;
import com.danielarrais.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    private final CozinhaRepository cozinhaRepository;

    public CozinhaController(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    @GetMapping()
    public List<Cozinha> listar() {
        return cozinhaRepository.all();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        Cozinha cozinha = cozinhaRepository.find(id);

        return Optional
                .ofNullable(cozinha)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody Cozinha cozinha) {
        cozinhaRepository.add(cozinha);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        return Optional
                .ofNullable(cozinhaRepository.find(id))
                .map(cozinhaAtual -> {
                    BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
                    cozinhaRepository.add(cozinhaAtual);

                    return ResponseEntity.ok((Void) null);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        return Optional
                .ofNullable(cozinhaRepository.find(id))
                .map(cozinha -> {
                    cozinhaRepository.remove(cozinha);

                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body((Void) null);
                }).orElse(ResponseEntity.notFound().build());
    }
}
