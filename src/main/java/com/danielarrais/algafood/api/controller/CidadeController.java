package com.danielarrais.algafood.api.controller;

import com.danielarrais.algafood.domain.model.Cidade;
import com.danielarrais.algafood.domain.service.CidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
    private final CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping()
    public List<Cidade> listar() {
        return cidadeService.listar();
    }

    @GetMapping("/{id}")
    public Cidade buscar(@PathVariable Long id) {
        return cidadeService.buscarObrigatorio(id);
    }

    @PostMapping
    public void adicionar(@RequestBody @Valid Cidade cidade) {
        cidadeService.salvar(cidade);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid Cidade cidade) {
        cidadeService.atualizar(id, cidade);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizarParcial(@PathVariable Long id, @RequestBody @Valid Map<String, Object> valores) {
        cidadeService.atualizar(id, valores);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        cidadeService.remover(id);
    }
}