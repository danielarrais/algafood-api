package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.RegistroEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.Cozinha;
import com.danielarrais.algafood.domain.repository.CozinhaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.danielarrais.algafood.util.CustomBeansUtils.copyNonNullValues;

@Service
public class CozinhaService {
    private final CozinhaRepository cozinhaRepository;

    public CozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    public Page<Cozinha> listar(Pageable pageable) {
        return cozinhaRepository.findAll(pageable);
    }

    public Optional<Cozinha> buscar(long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId);
    }

    public Cozinha buscarObrigatorio(long cozinhaId) {
        return buscar(cozinhaId).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException("Cozinha", cozinhaId);
        });
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public void atualizar(Long id, Cozinha cozinha) {
        var cozinhaAtual = buscarObrigatorio(id);

        copyNonNullValues(cozinha, cozinhaAtual);
        salvar(cozinhaAtual);
    }

    @Transactional
    public void remover(Long id) {
        var cozinha = buscarObrigatorio(id);

        try {
            cozinhaRepository.delete(cozinha);
            cozinhaRepository.flush();
        } catch (DataIntegrityViolationException exception) {
            throw new RegistroEmUsoException(id);
        }
    }
}