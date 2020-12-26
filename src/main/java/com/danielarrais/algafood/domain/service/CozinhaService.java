package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.EntidadeEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Cozinha;
import com.danielarrais.algafood.domain.repository.CozinhaRepository;
import com.danielarrais.algafood.util.CustomBeansUtils;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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

    @SneakyThrows
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public Cozinha atualizar(Long id, Cozinha cozinha) {
        return buscar(id).map(cozinhaAtual -> {
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            return salvar(cozinhaAtual);
        }).orElseThrow(() -> {
            throw new RegistroNaoEncontradaException(id);
        });
    }

    public Cozinha atualizar(Long id, Map<String, Object> propertiesAndValues) {
        return buscar(id).map(cozinhaAtual -> {
            CustomBeansUtils.mergeValues(propertiesAndValues, cozinhaAtual);
            return salvar(cozinhaAtual);
        }).orElseThrow(() -> {
            throw new RegistroNaoEncontradaException(id);
        });
    }

    public void remover(Long id) {
        try {
            cozinhaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new RegistroNaoEncontradaException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(id);
        }
    }
}