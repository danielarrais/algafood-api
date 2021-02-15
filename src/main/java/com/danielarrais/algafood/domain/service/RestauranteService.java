package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.RegistroEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.RestauranteRepository;
import com.danielarrais.algafood.domain.service.validation.RestauranteValidation;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.danielarrais.algafood.util.CustomBeansUtils.copyNonNullValues;

@Service
public class RestauranteService {
    private final RestauranteRepository restauranteRepository;
    private final RestauranteValidation restauranteValidation;

    private final FormaPagamentoService formaPagamentoService;
    private final UsuarioService usuarioService;

    public RestauranteService(RestauranteRepository restauranteRepository, RestauranteValidation restauranteValidation, FormaPagamentoService formaPagamentoService, UsuarioService usuarioService) {
        this.restauranteRepository = restauranteRepository;
        this.restauranteValidation = restauranteValidation;
        this.formaPagamentoService = formaPagamentoService;
        this.usuarioService = usuarioService;
    }

    public Page<Restaurante> listar(Pageable pageable) {
        return restauranteRepository.findAll(pageable);
    }

    public Optional<Restaurante> buscar(long restauranteId) {
        return restauranteRepository.findById(restauranteId);
    }

    public Restaurante buscarObrigatorio(long restauranteId) {
        return buscar(restauranteId).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException("Restaurante", restauranteId);
        });
    }

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
    public void ativar(List<Long> ids) {
        ids.forEach(this::ativar);
    }

    @Transactional
    public void inativar(List<Long> ids) {
        ids.forEach(this::inativar);
    }

    @Transactional
    public void abrir(Long id) {
        var restaurante = buscarObrigatorio(id);
        restaurante.abrir();
    }

    @Transactional
    public void fechar(Long id) {
        var restaurante = buscarObrigatorio(id);
        restaurante.fechar();
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
    public void associarFormaPagamento(Long restauranteId, Long idFormaPagamento) {
        var restaurante = buscarObrigatorio(restauranteId);
        var formaPagamento = formaPagamentoService.buscarObrigatorio(idFormaPagamento);

        restaurante.adicionarFormaPagamento(formaPagamento);
    }

    @Transactional
    public void desassociarFormaPagamento(Long restauranteId, Long idFormaPagamento) {
        var restaurante = buscarObrigatorio(restauranteId);
        var formaPagamento = formaPagamentoService.buscarObrigatorio(idFormaPagamento);

        restaurante.removerFormaPagamento(formaPagamento);
    }

    @Transactional
    public void associarResponsavel(Long restauranteId, Long idUsuario) {
        var restaurante = buscarObrigatorio(restauranteId);
        var usuario = usuarioService.buscarObrigatorio(idUsuario);

        restaurante.adicionarResponsavel(usuario);
    }

    @Transactional
    public void desassociarResponsavel(Long restauranteId, Long idUsuario) {
        var restaurante = buscarObrigatorio(restauranteId);
        var usuario = usuarioService.buscarObrigatorio(idUsuario);

        restaurante.removerResponsavel(usuario);
    }
}