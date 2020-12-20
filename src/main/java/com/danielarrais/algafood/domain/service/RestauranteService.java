package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.CidadeRepository;
import com.danielarrais.algafood.domain.repository.CozinhaRepository;
import com.danielarrais.algafood.domain.repository.RestauranteRepository;
import com.danielarrais.algafood.util.CustomBeansUtils;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class RestauranteService {
    private final RestauranteRepository restauranteRepository;
    private final CozinhaRepository cozinhaRepository;
    private final CidadeRepository cidadeRepository;

    public RestauranteService(RestauranteRepository restauranteRepository, CozinhaRepository cozinhaRepository, CidadeRepository cidadeRepository) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaRepository = cozinhaRepository;
        this.cidadeRepository = cidadeRepository;
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> buscar(long restauranteId) {
        return restauranteRepository.findById(restauranteId);
    }

    @SneakyThrows
    public Restaurante salvar(Restaurante restaurante) {
        validateExistsCozinha(restaurante);
        validateExistsCidade(restaurante);

        return restauranteRepository.save(restaurante);
    }

    public void validateExistsCozinha(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        boolean existsCozinha = cozinhaRepository.existsById(cozinhaId);

        if (!existsCozinha) {
            throw new EntidadeNaoEncontradaException("Cozinha", cozinhaId, true);
        }
    }

    public void validateExistsCidade(Restaurante restaurante) {
        if (Objects.isNull(restaurante) ||
                Objects.isNull(restaurante.getEndereco()) ||
                Objects.isNull(restaurante.getEndereco().getCidade())) {
            return;
        }

        Long cidadeId = restaurante.getEndereco().getCidade().getId();
        boolean existsCidade = cidadeRepository.existsById(cidadeId);

        if (!existsCidade) {
            throw new EntidadeNaoEncontradaException("Cidade", cidadeId, true);
        }
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