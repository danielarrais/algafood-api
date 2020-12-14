package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.EntidadeEmUsoException;
import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Cidade;
import com.danielarrais.algafood.domain.model.Estado;
import com.danielarrais.algafood.domain.repository.CidadeRepository;
import com.danielarrais.algafood.domain.repository.EstadoRepository;
import com.danielarrais.algafood.util.CustomBeansUtils;
import com.danielarrais.algafood.util.ExceptionUtils;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CidadeService {
    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;

    public CidadeService(CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }

    public List<Cidade> listar() {
        return cidadeRepository.listar();
    }

    public Cidade buscar(long cidadeId) {
        return cidadeRepository.buscar(cidadeId);
    }

    @SneakyThrows
    public void salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.buscar(estadoId);

        ExceptionUtils.throwIsNull(estado, new EntidadeNaoEncontradaException("Estado", estadoId, true));

        cidadeRepository.salvar(cidade);
    }

    public void atualizar(Long id, Cidade cidade) {
        Cidade cidadeAtual = buscar(id);

        if (Objects.isNull(cidadeAtual)) {
            throw new EntidadeNaoEncontradaException(id);
        }

        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        salvar(cidadeAtual);
    }

    public void atualizar(Long id, Map<String, Object> propertiesAndValues) {
        Cidade cozinhaAtual = buscar(id);

        if (Objects.isNull(cozinhaAtual)) {
            throw new EntidadeNaoEncontradaException(id);
        }

        CustomBeansUtils.mergeValues(propertiesAndValues, cozinhaAtual);

        salvar(cozinhaAtual);
    }

    public void remover(Long id) {
        try {
            cidadeRepository.remover(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(id);
        }
    }
}