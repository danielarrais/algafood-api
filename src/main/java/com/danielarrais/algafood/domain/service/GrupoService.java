package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.EntidadeEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Grupo;
import com.danielarrais.algafood.domain.repository.GrupoRepository;
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
public class GrupoService {
    private final GrupoRepository grupoRepository;

    public GrupoService(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    public List<Grupo> listar() {
        return grupoRepository.findAll();
    }

    public Optional<Grupo> buscar(long grupoId) {
        return grupoRepository.findById(grupoId);
    }

    public Grupo buscarObrigatorio(long grupoId) {
        return buscar(grupoId).orElseThrow(() -> {
            throw new RegistroNaoEncontradaException(grupoId);
        });
    }

    @SneakyThrows
    public void salvar(Grupo grupo) {
        grupoRepository.save(grupo);
    }

    public void atualizar(Long id, Grupo grupo) {
        buscar(id).map(grupoAtual -> {
            BeanUtils.copyProperties(grupo, grupoAtual, "id");
            return grupoRepository.save(grupoAtual);
        }).orElseThrow(() -> {
            throw new RegistroNaoEncontradaException(id);
        });
    }

    public void atualizar(Long id, Map<String, Object> propertiesAndValues) {
        buscar(id).map(grupoAtual -> {
            CustomBeansUtils.mergeValues(propertiesAndValues, grupoAtual);
            return grupoRepository.save(grupoAtual);
        }).orElseThrow(() -> {
            throw new RegistroNaoEncontradaException(id);
        });
    }

    public void remover(Long id) {
        try {
            grupoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new RegistroNaoEncontradaException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(id);
        }
    }
}