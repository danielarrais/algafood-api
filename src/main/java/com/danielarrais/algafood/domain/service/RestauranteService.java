package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.RegistroEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.RestauranteRepository;
import com.danielarrais.algafood.domain.service.validation.RastauranteValidation;
import com.danielarrais.algafood.util.CustomBeansUtils;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RestauranteService {
    private final RestauranteRepository restauranteRepository;
    private final RastauranteValidation rastauranteValidation;

    public RestauranteService(RestauranteRepository restauranteRepository, RastauranteValidation rastauranteValidation) {
        this.restauranteRepository = restauranteRepository;
        this.rastauranteValidation = rastauranteValidation;
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> buscar(long restauranteId) {
        return restauranteRepository.findById(restauranteId);
    }

    public Restaurante buscarObrigatorio(long restauranteId) {
        return buscar(restauranteId).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException(restauranteId);
        });
    }

    @SneakyThrows
    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        rastauranteValidation.validate(restaurante);

        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public Restaurante atualizar(Long id, Restaurante restaurante) {
        return buscar(id).map(restauranteAtual -> {
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
            return salvar(restauranteAtual);
        }).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException(id);
        });
    }

    @Transactional
    public Restaurante atualizar(Long id, Map<String, Object> propertiesAndValues) {
        return buscar(id).map(restauranteAtual -> {
            CustomBeansUtils.mergeValues(propertiesAndValues, restauranteAtual);

            rastauranteValidation.smartValidate(restauranteAtual);

            return salvar(restauranteAtual);
        }).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException(id);
        });
    }

    @Transactional
    public void remover(Long id) {
        try {
            restauranteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new RegistroNaoEncontradoException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new RegistroEmUsoException(id);
        }
    }

    public List<Restaurante> findComFreteGratis() {
        return restauranteRepository.findComFreteGratis();
    }
}