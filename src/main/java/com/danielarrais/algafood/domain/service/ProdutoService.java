package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.Produto;
import com.danielarrais.algafood.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.danielarrais.algafood.util.CustomBeansUtils.copyNonNullValues;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final RestauranteService restauranteService;

    public ProdutoService(ProdutoRepository produtoRepository, RestauranteService restauranteService) {
        this.produtoRepository = produtoRepository;
        this.restauranteService = restauranteService;
    }

    public Optional<Produto> buscar(Long restauranteId, long produtoId) {
        return produtoRepository.findById(restauranteId, produtoId);
    }

    public Produto buscarObrigatorio(long produtoId, Long restauranteId) {
        return buscar(restauranteId, produtoId).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException("Produto", produtoId);
        });
    }

    @Transactional
    public void salvar(Long restauranteId, Produto produto) {
        var restaurante = restauranteService.buscarObrigatorio(restauranteId);

        produto.setRestaurante(restaurante);
        produtoRepository.save(produto);
    }

    @Transactional
    public void atualizar(Long id, Long restauranteId, Produto produto) {
        var produtoAtual = buscarObrigatorio(id, restauranteId);

        copyNonNullValues(produto, produtoAtual);
        produtoRepository.save(produtoAtual);
    }
}