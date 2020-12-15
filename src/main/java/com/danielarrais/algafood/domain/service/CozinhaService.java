package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Cozinha;
import com.danielarrais.algafood.domain.repository.CozinhaRepository;
import com.danielarrais.algafood.util.CustomBeansUtils;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
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
    public void salvar(Cozinha cozinha) {
        cozinhaRepository.save(cozinha);
    }

    public void atualizar(Long id, Cozinha cozinha) {
        buscar(id).map(cozinhaAtual -> {
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            return cozinhaRepository.save(cozinhaAtual);
        }).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException(id);
        });
    }

    public void atualizar(Long id, Map<String, Object> propertiesAndValues) {
        buscar(id).map(cozinhaAtual -> {
            CustomBeansUtils.mergeValues(propertiesAndValues, cozinhaAtual);
            return cozinhaRepository.save(cozinhaAtual);
        }).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException(id);
        });
    }

    public void remover(Long id) {
        cozinhaRepository.deleteById(id);
    }
}