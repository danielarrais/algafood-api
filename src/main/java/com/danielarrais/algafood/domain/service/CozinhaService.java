package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.EntidadeEmUsoException;
import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Cozinha;
import com.danielarrais.algafood.domain.repository.CozinhaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

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
        cozinhaRepository.salvar(cozinha);
    }

    public void remover(Long id) {
        try {
            cozinhaRepository.remover(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(id);
        }
    }
}
