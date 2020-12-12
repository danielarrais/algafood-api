package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.EntidadeEmUsoException;
import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Cozinha;
import com.danielarrais.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CozinhaService {
    private final CozinhaRepository cozinhaRepository;

    public CozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    public Cozinha buscar(long cozinhaId) {
        return cozinhaRepository.buscar(cozinhaId);
    }

    public void salvar(Cozinha cozinha) {
        try {
            cozinhaRepository.salvar(cozinha);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(cozinha.getId());
        }
    }

    public void atualizar(Long id, Cozinha cozinha) {
        Cozinha cozinhaAtual = buscar(id);

        if (Objects.isNull(cozinhaAtual)) {
            throw new EntidadeNaoEncontradaException(id);
        }

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        salvar(cozinhaAtual);
    }

    public void remover(Long id) {
        try {
            cozinhaRepository.remover(id);
        } catch (EmptyResultDataAccessException exception) {

        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(id);
        }
    }
}
