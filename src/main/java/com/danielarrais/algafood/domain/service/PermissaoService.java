package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Permissao;
import com.danielarrais.algafood.domain.repository.PermissaoRepository;
import com.danielarrais.algafood.util.CustomBeansUtils;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PermissaoService {
    private final PermissaoRepository permissaoRepository;

    public PermissaoService(PermissaoRepository permissaoRepository) {
        this.permissaoRepository = permissaoRepository;
    }

    public List<Permissao> listar() {
        return permissaoRepository.findAll();
    }

    public Optional<Permissao> buscar(long permissaoId) {
        return permissaoRepository.findById(permissaoId);
    }

    @SneakyThrows
    public void salvar(Permissao permissao) {
        permissaoRepository.save(permissao);
    }

    public void atualizar(Long id, Permissao permissao) {
        buscar(id).map(permissaoAtual -> {
            BeanUtils.copyProperties(permissao, permissaoAtual, "id");
            return permissaoRepository.save(permissaoAtual);
        }).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException(id);
        });
    }

    public void atualizar(Long id, Map<String, Object> propertiesAndValues) {
        buscar(id).map(permissaoAtual -> {
            CustomBeansUtils.mergeValues(propertiesAndValues, permissaoAtual);
            return permissaoRepository.save(permissaoAtual);
        }).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException(id);
        });
    }

    public void remover(Long id) {
        try {
            permissaoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(id);
        }
    }
}