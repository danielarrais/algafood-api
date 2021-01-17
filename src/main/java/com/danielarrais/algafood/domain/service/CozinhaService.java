package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.RegistroEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.Cozinha;
import com.danielarrais.algafood.domain.repository.CozinhaRepository;
import com.danielarrais.algafood.util.CustomBeansUtils;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CozinhaService {
    private final CozinhaRepository cozinhaRepository;

    public CozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    public Optional<Cozinha> buscar(long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId);
    }

    public Cozinha buscarObrigatorio(long cozinhaId) {
        return buscar(cozinhaId).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException(cozinhaId);
        });
    }

    @SneakyThrows
    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public Cozinha atualizar(Long id, Cozinha cozinha) {
        return buscar(id).map(cozinhaAtual -> {
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            return salvar(cozinhaAtual);
        }).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException(id);
        });
    }

    @Transactional
    public Cozinha atualizar(Long id, Map<String, Object> propertiesAndValues) {
        return buscar(id).map(cozinhaAtual -> {
            CustomBeansUtils.mergeValues(propertiesAndValues, cozinhaAtual);
            return salvar(cozinhaAtual);
        }).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException(id);
        });
    }

    @Transactional
    public void remover(Long id) {
        try {
            cozinhaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new RegistroNaoEncontradoException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new RegistroEmUsoException(id);
        }
    }
}