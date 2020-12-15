package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Cozinha;
import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.CozinhaRepository;
import com.danielarrais.algafood.domain.repository.RestauranteRepository;
import com.danielarrais.algafood.util.CustomBeansUtils;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RestauranteService {
    private final RestauranteRepository restauranteRepository;
    private final CozinhaRepository cozinhaRepository;

    public RestauranteService(RestauranteRepository restauranteRepository, CozinhaRepository cozinhaRepository) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaRepository = cozinhaRepository;
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> buscar(long restauranteId) {
        return restauranteRepository.findById(restauranteId);
    }

    @SneakyThrows
    public void salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);

        cozinha.orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException("Cozinha", cozinhaId, true);
        });

        restauranteRepository.save(restaurante);
    }

    public void atualizar(Long id, Restaurante restaurante) {
        buscar(id).map(restauranteAtual -> {
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
            return restauranteRepository.save(restauranteAtual);
        }).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException(id);
        });
    }

    public void atualizar(Long id, Map<String, Object> propertiesAndValues) {
        buscar(id).map(restauranteAtual -> {
            CustomBeansUtils.mergeValues(propertiesAndValues, restauranteAtual);
            return restauranteRepository.save(restauranteAtual);
        }).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException(id);
        });
    }

    public void remover(Long id) {
        restauranteRepository.deleteById(id);
    }
}