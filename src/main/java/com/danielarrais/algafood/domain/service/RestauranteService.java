package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.RegistroEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.RestauranteRepository;
import com.danielarrais.algafood.domain.service.validation.RestauranteValidation;
import lombok.SneakyThrows;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.danielarrais.algafood.util.CustomBeansUtils.copyNonNullValues;
import static com.danielarrais.algafood.util.CustomBeansUtils.mergeValues;

@Service
public class RestauranteService {
    private final RestauranteRepository restauranteRepository;
    private final RestauranteValidation restauranteValidation;

    private final FormaPagamentoService formaPagamentoService;

    public RestauranteService(RestauranteRepository restauranteRepository, RestauranteValidation restauranteValidation, FormaPagamentoService formaPagamentoService) {
        this.restauranteRepository = restauranteRepository;
        this.restauranteValidation = restauranteValidation;
        this.formaPagamentoService = formaPagamentoService;
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> buscar(long restauranteId) {
        return restauranteRepository.findById(restauranteId);
    }

    public Restaurante buscarObrigatorio(long restauranteId) {
        return buscar(restauranteId).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException("Restaurante", restauranteId);
        });
    }

    @SneakyThrows
    @Transactional
    public void salvar(Restaurante restaurante) {
        restauranteValidation.validate(restaurante);
        restauranteRepository.save(restaurante);
    }

    @Transactional
    public void atualizar(Long id, Restaurante restaurante) {
        var restauranteAtual = buscarObrigatorio(id);

        copyNonNullValues(restaurante, restauranteAtual);
        salvar(restauranteAtual);
    }

    @Transactional
    public void ativar(Long id) {
        var restaurante = buscarObrigatorio(id);
        restaurante.ativar();
    }

    @Transactional
    public void inativar(Long id) {
        var restaurante = buscarObrigatorio(id);
        restaurante.inativar();
    }

    @Transactional
    public void atualizar(Long id, Map<String, Object> propertiesAndValues) {
        var restauranteAtual = buscarObrigatorio(id);

        mergeValues(propertiesAndValues, restauranteAtual);
        restauranteValidation.smartValidate(restauranteAtual);

        salvar(restauranteAtual);
    }

    @Transactional
    public void remover(Long id) {
        var restaurante = buscarObrigatorio(id);
        try {
            restauranteRepository.delete(restaurante);
            restauranteRepository.flush();
        } catch (DataIntegrityViolationException exception) {
            throw new RegistroEmUsoException(id);
        }
    }

    @Transactional
    public void adicionarFormaPagamento(Long restauranteId, Long idFormaPagamento) {
        var restaurante = buscarObrigatorio(restauranteId);
        var formaPagamento = formaPagamentoService.buscarObrigatorio(idFormaPagamento);

        restaurante.adicionarFormaPagamento(formaPagamento);
    }

    @Transactional
    public void removerFormaPagamento(Long restauranteId, Long idFormaPagamento) {
        var restaurante = buscarObrigatorio(restauranteId);
        var formaPagamento = formaPagamentoService.buscarObrigatorio(idFormaPagamento);

        restaurante.removerFormaPagamento(formaPagamento);
    }
}