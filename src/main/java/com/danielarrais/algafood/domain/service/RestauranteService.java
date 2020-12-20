package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.CidadeRepository;
import com.danielarrais.algafood.domain.repository.CozinhaRepository;
import com.danielarrais.algafood.domain.repository.RestauranteRepository;
import com.danielarrais.algafood.domain.service.validation.RastauranteValidation;
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
    private final RastauranteValidation rastauranteValidation;

    public RestauranteService(RestauranteRepository restauranteRepository, CozinhaRepository cozinhaRepository, CidadeRepository cidadeRepository, RastauranteValidation rastauranteValidation) {
        this.restauranteRepository = restauranteRepository;
        this.rastauranteValidation = rastauranteValidation;
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> buscar(long restauranteId) {
        return restauranteRepository.findById(restauranteId);
    }

    @SneakyThrows
    public Restaurante salvar(Restaurante restaurante) {
        rastauranteValidation.validateExistenceCozinha(restaurante);
        rastauranteValidation.validateExistenceCidade(restaurante);

        return restauranteRepository.save(restaurante);
    }

    public Restaurante atualizar(Long id, Restaurante restaurante) {
        return buscar(id).map(restauranteAtual -> {
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
            return salvar(restauranteAtual);
        }).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException(id);
        });
    }

    public Restaurante atualizar(Long id, Map<String, Object> propertiesAndValues) {
        return buscar(id).map(restauranteAtual -> {
            CustomBeansUtils.mergeValues(propertiesAndValues, restauranteAtual);
            return salvar(restauranteAtual);
        }).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException(id);
        });
    }

    public void remover(Long id) {
        restauranteRepository.deleteById(id);
    }

    public List<Restaurante> findComFreteGratis() {
        return restauranteRepository.findComFreteGratis();
    }
}