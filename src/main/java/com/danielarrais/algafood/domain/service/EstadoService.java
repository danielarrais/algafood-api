package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.RegistroEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.Estado;
import com.danielarrais.algafood.domain.repository.EstadoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Page<Estado> listar(Pageable pageable) {
        return estadoRepository.findAll(pageable);
    }

    public Optional<Estado> buscar(long estadoId) {
        return estadoRepository.findById(estadoId);
    }

    public Estado buscarObrigatorio(long estadoId) {
        return buscar(estadoId).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException("Estado", estadoId);
        });
    }

    @Transactional
    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Transactional
    public void atualizar(Long id, Estado estado) {
        var estadoAtual = buscarObrigatorio(id);

        copyNonNullValues(estado, estadoAtual);
        salvar(estadoAtual);
    }

    @Transactional
    public void atualizar(Long id, Map<String, Object> propertiesAndValues) {
        var estadoAtual = buscarObrigatorio(id);

        mergeValues(propertiesAndValues, estadoAtual);
        salvar(estadoAtual);
    }

    @Transactional
    public void remover(Long id) {
        var estado = buscarObrigatorio(id);

        try {
            estadoRepository.delete(estado);
            estadoRepository.flush();
        } catch (DataIntegrityViolationException exception) {
            throw new RegistroEmUsoException(id);
        }
    }
}