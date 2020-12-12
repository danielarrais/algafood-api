package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.EntidadeEmUsoException;
import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Estado;
import com.danielarrais.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EstadoService {
    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    public Estado buscar(long estadoId) {
        return estadoRepository.buscar(estadoId);
    }

    public void salvar(Estado estado) {
        estadoRepository.salvar(estado);
    }

    public void atualizar(Long id, Estado estado) {
        Estado estadoAtual = buscar(id);

        if (Objects.isNull(estadoAtual)) {
            throw new EntidadeNaoEncontradaException(id);
        }

        BeanUtils.copyProperties(estado, estadoAtual, "id");

        salvar(estadoAtual);
    }

    public void remover(Long id) {
        try {
            estadoRepository.remover(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(id);
        }
    }
}