package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Produto;
import com.danielarrais.algafood.domain.repository.ProdutoRepository;
import com.danielarrais.algafood.util.CustomBeansUtils;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscar(long produtoId) {
        return produtoRepository.findById(produtoId);
    }

    @SneakyThrows
    public void salvar(Produto produto) {
        produtoRepository.save(produto);
    }

    public void atualizar(Long id, Produto produto) {
        buscar(id).map(produtoAtual -> {
            BeanUtils.copyProperties(produto, produtoAtual, "id");
            return produtoRepository.save(produtoAtual);
        }).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException(id);
        });
    }

    public void atualizar(Long id, Map<String, Object> propertiesAndValues) {
        buscar(id).map(produtoAtual -> {
            CustomBeansUtils.mergeValues(propertiesAndValues, produtoAtual);
            return produtoRepository.save(produtoAtual);
        }).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException(id);
        });
    }

    public void remover(Long id) {
        try {
            produtoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(id);
        }
    }
}