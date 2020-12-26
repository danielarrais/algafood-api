package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.EntidadeEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradaException;
import com.danielarrais.algafood.domain.model.FormaPagamento;
import com.danielarrais.algafood.domain.repository.FormaPagamentoRepository;
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
public class FormaPagamentoService {
    private final FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamentoService(FormaPagamentoRepository formaPagamentoRepository) {
        this.formaPagamentoRepository = formaPagamentoRepository;
    }

    public List<FormaPagamento> listar() {
        return formaPagamentoRepository.findAll();
    }

    public Optional<FormaPagamento> buscar(long formaPagamentoId) {
        return formaPagamentoRepository.findById(formaPagamentoId);
    }

    public FormaPagamento buscarObrigatorio(long formaPagamentoId) {
        return buscar(formaPagamentoId).orElseThrow(() -> {
            throw new RegistroNaoEncontradaException(formaPagamentoId);
        });
    }

    @SneakyThrows
    public void salvar(FormaPagamento formaPagamento) {
        formaPagamentoRepository.save(formaPagamento);
    }

    public void atualizar(Long id, FormaPagamento formaPagamento) {
        buscar(id).map(formaPagamentoAtual -> {
            BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual, "id");
            return formaPagamentoRepository.save(formaPagamentoAtual);
        }).orElseThrow(() -> {
            throw new RegistroNaoEncontradaException(id);
        });
    }

    public void atualizar(Long id, Map<String, Object> propertiesAndValues) {
        buscar(id).map(formaPagamentoAtual -> {
            CustomBeansUtils.mergeValues(propertiesAndValues, formaPagamentoAtual);
            return formaPagamentoRepository.save(formaPagamentoAtual);
        }).orElseThrow(() -> {
            throw new RegistroNaoEncontradaException(id);
        });
    }

    public void remover(Long id) {
        try {
            formaPagamentoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new RegistroNaoEncontradaException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(id);
        }
    }
}