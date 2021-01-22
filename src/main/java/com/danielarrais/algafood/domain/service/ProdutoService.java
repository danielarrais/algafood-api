package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.Produto;
import com.danielarrais.algafood.domain.repository.ProdutoRepository;
import com.danielarrais.algafood.domain.service.validation.ProdutoValidation;
import lombok.SneakyThrows;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.danielarrais.algafood.util.CustomBeansUtils.copyNonNullValues;
import static com.danielarrais.algafood.util.CustomBeansUtils.mergeValues;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final ProdutoValidation produtoValidation;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoValidation produtoValidation) {
        this.produtoRepository = produtoRepository;
        this.produtoValidation = produtoValidation;
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscar(long produtoId) {
        return produtoRepository.findById(produtoId);
    }

    public Produto buscarObrigatorio(long produtoId) {
        return buscar(produtoId).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException(produtoId);
        });
    }

    @SneakyThrows
    @Transactional
    public void salvar(Produto produto) {
        produtoValidation.validateTheRestauranteExistence(produto);

        produtoRepository.save(produto);
    }

    @Transactional
    public void atualizar(Long id, Produto produto) {
        buscar(id).map(produtoAtual -> {
            copyNonNullValues(produto, produtoAtual);
            return produtoRepository.save(produtoAtual);
        }).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException(id);
        });
    }

    @Transactional
    public void atualizar(Long id, Map<String, Object> propertiesAndValues) {
        buscar(id).map(produtoAtual -> {
            mergeValues(propertiesAndValues, produtoAtual);
            return produtoRepository.save(produtoAtual);
        }).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException(id);
        });
    }

    @Transactional
    public void remover(Long id) {
        try {
            produtoRepository.deleteById(id);
            produtoRepository.flush();
        } catch (EmptyResultDataAccessException exception) {
            throw new RegistroNaoEncontradoException(id);
        }
    }
}