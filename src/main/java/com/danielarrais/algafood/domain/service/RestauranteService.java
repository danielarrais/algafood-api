package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RestauranteService {
    private final RestauranteRepository restauranteRepository;

    public RestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    public Restaurante buscar(long restauranteId) {
        return restauranteRepository.buscar(restauranteId);
    }

    public void salvar(Restaurante restaurante) {
        try {
            restauranteRepository.salvar(restaurante);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(restaurante.getId());
        }
    }

    public void atualizar(Long id, Restaurante restaurante) {
        Restaurante restauranteAtual = buscar(id);

        if (Objects.isNull(restauranteAtual)) {
            throw new EntidadeNaoEncontradaException(id);
        }

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

        salvar(restauranteAtual);
    }

    public void remover(Long id) {
        try {
            restauranteRepository.remover(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(id);
        }
    }
}