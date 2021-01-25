package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.RegistroEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.FormaPagamento;
import com.danielarrais.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.danielarrais.algafood.util.CustomBeansUtils.copyNonNullValues;
import static com.danielarrais.algafood.util.CustomBeansUtils.mergeValues;

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
            throw new RegistroNaoEncontradoException("Forma de pagamento", formaPagamentoId);
        });
    }

    @Transactional
    public void salvar(FormaPagamento formaPagamento) {
        formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public void atualizar(Long id, FormaPagamento formaPagamento) {
        var formaPagamentoAtual = buscarObrigatorio(id);

        copyNonNullValues(formaPagamento, formaPagamentoAtual);
        formaPagamentoRepository.save(formaPagamentoAtual);
    }

    @Transactional
    public void atualizar(Long id, Map<String, Object> propertiesAndValues) {
        var formaPagamentoAtual = buscarObrigatorio(id);

        mergeValues(propertiesAndValues, formaPagamentoAtual);
        formaPagamentoRepository.save(formaPagamentoAtual);
    }

    @Transactional
    public void remover(Long id) {
        var formaPagamentoAtual = buscarObrigatorio(id);

        try {
            formaPagamentoRepository.delete(formaPagamentoAtual);
            formaPagamentoRepository.flush();
        } catch (DataIntegrityViolationException exception) {
            throw new RegistroEmUsoException(id);
        }
    }
}