package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.DependenciaNaoEncontradaException;
import com.danielarrais.algafood.domain.exception.RegistroEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.Cidade;
import com.danielarrais.algafood.domain.repository.CidadeRepository;
import com.danielarrais.algafood.domain.repository.EstadoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.danielarrais.algafood.util.CustomBeansUtils.copyNonNullValues;
import static com.danielarrais.algafood.util.CustomBeansUtils.mergeValues;

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

    public Cidade buscarObrigatorio(long cidadeId) {
        return buscar(cidadeId).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException("Cidade", cidadeId);
        });
    }

    @Transactional
    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();

        estadoRepository.findById(estadoId)
                .orElseThrow(() -> new DependenciaNaoEncontradaException("Estado", estadoId));

        return cidadeRepository.save(cidade);
    }

    @Transactional
    public void atualizar(Long id, Cidade cidade) {
        var cidadeAtual = buscarObrigatorio(id);

        copyNonNullValues(cidade, cidadeAtual);
        salvar(cidadeAtual);
    }

    @Transactional
    public void atualizar(Long id, Map<String, Object> propertiesAndValues) {
        var cidadeAtual = buscarObrigatorio(id);

        mergeValues(propertiesAndValues, cidadeAtual);
        salvar(cidadeAtual);
    }

    @Transactional
    public void remover(Long id) {
        var cidade = buscarObrigatorio(id);

        try {
            cidadeRepository.delete(cidade);
            cidadeRepository.flush();
        } catch (DataIntegrityViolationException exception) {
            throw new RegistroEmUsoException(id);
        }
    }
}