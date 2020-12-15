package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Cidade;
import com.danielarrais.algafood.domain.repository.CidadeRepository;
import com.danielarrais.algafood.domain.repository.EstadoRepository;
import com.danielarrais.algafood.util.CustomBeansUtils;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class
CidadeService {
    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;

    public CidadeService(CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Optional<Cidade> buscar(long cidadeId) {
        return cidadeRepository.findById(cidadeId);
    }

    @SneakyThrows
    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();

        estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Estado", estadoId, true));

        return cidadeRepository.save(cidade);
    }

    public Cidade atualizar(Long id, Cidade cidade) {
        return buscar(id).map(cidadeAtual -> {
            BeanUtils.copyProperties(cidade, cidadeAtual, "id");
            return salvar(cidadeAtual);
        }).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException(id);
        });
    }

    public Cidade atualizar(Long id, Map<String, Object> propertiesAndValues) {
        return buscar(id).map(cidadeAtual -> {
            CustomBeansUtils.mergeValues(propertiesAndValues, cidadeAtual);
            return salvar(cidadeAtual);
        }).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException(id);
        });
    }

    public void remover(Long id) {
        cidadeRepository.deleteById(id);
    }
}