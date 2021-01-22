package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.RegistroEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.Estado;
import com.danielarrais.algafood.domain.repository.EstadoRepository;
import lombok.SneakyThrows;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.danielarrais.algafood.util.CustomBeansUtils.copyNonNullValues;
import static com.danielarrais.algafood.util.CustomBeansUtils.mergeValues;

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
            throw new RegistroNaoEncontradoException(estadoId);
        });
    }

    @SneakyThrows
    @Transactional
    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Transactional
    public Estado atualizar(Long id, Estado estado) {
        return buscar(id).map(estadoAtual -> {
            copyNonNullValues(estado, estadoAtual);
            return salvar(estadoAtual);
        }).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException(id);
        });
    }

    @Transactional
    public Estado atualizar(Long id, Map<String, Object> propertiesAndValues) {
        return buscar(id).map(estadoAtual -> {
            mergeValues(propertiesAndValues, estadoAtual);
            return salvar(estadoAtual);
        }).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException(id);
        });
    }

    @Transactional
    public void remover(Long id) {
        try {
            estadoRepository.deleteById(id);
            estadoRepository.flush();
        } catch (EmptyResultDataAccessException exception) {
            throw new RegistroNaoEncontradoException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new RegistroEmUsoException(id);
        }
    }
}