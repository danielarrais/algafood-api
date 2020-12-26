package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.EntidadeEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Estado;
import com.danielarrais.algafood.domain.repository.EstadoRepository;
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
public class EstadoService {
    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Optional<Estado> buscar(long estadoId) {
        return estadoRepository.findById(estadoId);
    }

    public Estado buscarObrigatorio(long estadoId) {
        return buscar(estadoId).orElseThrow(() -> {
            throw new RegistroNaoEncontradaException(estadoId);
        });
    }

    @SneakyThrows
    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado atualizar(Long id, Estado estado) {
        return buscar(id).map(estadoAtual -> {
            BeanUtils.copyProperties(estado, estadoAtual, "id");
            return salvar(estadoAtual);
        }).orElseThrow(() -> {
            throw new RegistroNaoEncontradaException(id);
        });
    }

    public Estado atualizar(Long id, Map<String, Object> propertiesAndValues) {
        return buscar(id).map(estadoAtual -> {
            CustomBeansUtils.mergeValues(propertiesAndValues, estadoAtual);
            return salvar(estadoAtual);
        }).orElseThrow(() -> {
            throw new RegistroNaoEncontradaException(id);
        });
    }

    public void remover(Long id) {
        try {
            estadoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new RegistroNaoEncontradaException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(id);
        }
    }
}