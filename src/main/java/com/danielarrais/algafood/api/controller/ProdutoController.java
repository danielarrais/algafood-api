package com.danielarrais.algafood.api.controller;

import com.danielarrais.algafood.api.dto.input.produto.ProdutoInput;
import com.danielarrais.algafood.api.dto.output.produto.ProdutoOutput;
import com.danielarrais.algafood.domain.model.Produto;
import com.danielarrais.algafood.domain.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping()
    public List<ProdutoOutput> listar() {
        var produtos = produtoService.listar();
        return mapper(produtos, ProdutoOutput.class);
    }

    @GetMapping("/{id}")
    public ProdutoOutput buscar(@PathVariable Long id) {
        var produto = produtoService.buscarObrigatorio(id);
        return mapper(produto, ProdutoOutput.class);
    }

    @PostMapping
    public void adicionar(@RequestBody @Valid ProdutoInput produtoInput) {
        var produto = mapper(produtoInput, Produto.class);
        produtoService.salvar(produto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoInput produtoInput) {
        var produto = mapper(produtoInput, Produto.class);
        produtoService.atualizar(id, produto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizarParcial(@PathVariable Long id, @RequestBody @Valid Map<String, Object> valores) {
        produtoService.atualizar(id, valores);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        produtoService.remover(id);
    }
}