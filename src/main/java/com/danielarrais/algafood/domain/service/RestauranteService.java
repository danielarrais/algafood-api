package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.RegistroEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.RestauranteRepository;
import com.danielarrais.algafood.domain.service.validation.RastauranteValidation;
import lombok.SneakyThrows;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public void salvar(Restaurante restaurante) {
        rastauranteValidation.validate(restaurante);
        restauranteRepository.save(restaurante);
    }

    @Transactional
    public void atualizar(Long id, Restaurante restaurante) {
        Restaurante restauranteAtual = buscarObrigatorio(id);
        copyNonNullValues(restaurante, restauranteAtual);

        salvar(restauranteAtual);
    }

    @Transactional
    public void ativar(Long id) {
        Restaurante restaurante = buscarObrigatorio(id);
        restaurante.ativar();
    }

    @Transactional
    public void inativar(Long id) {
        Restaurante restaurante = buscarObrigatorio(id);
        restaurante.inativar();
    }

    @Transactional
    public void atualizar(Long id, Map<String, Object> propertiesAndValues) {
        Restaurante restauranteAtual = buscarObrigatorio(id);
        mergeValues(propertiesAndValues, restauranteAtual);
        rastauranteValidation.smartValidate(restauranteAtual);

        salvar(restauranteAtual);
    }

    @Transactional
    public void remover(Long id) {
        try {
            restauranteRepository.deleteById(id);
            restauranteRepository.flush();
        } catch (EmptyResultDataAccessException exception) {
            throw new RegistroNaoEncontradoException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new RegistroEmUsoException(id);
        }
    }
}