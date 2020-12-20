package com.danielarrais.algafood.domain.service.validation;

import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Restaurante;
import com.danielarrais.algafood.domain.repository.CidadeRepository;
import com.danielarrais.algafood.domain.repository.CozinhaRepository;
import com.danielarrais.algafood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RastauranteValidation {
    private final CozinhaRepository cozinhaRepository;
    private final CidadeRepository cidadeRepository;

    public RastauranteValidation(RestauranteRepository restauranteRepository, CozinhaRepository cozinhaRepository, CidadeRepository cidadeRepository) {
        this.cozinhaRepository = cozinhaRepository;
        this.cidadeRepository = cidadeRepository;
    }

    public void validateExistenceCozinha(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        boolean existsCozinha = cozinhaRepository.existsById(cozinhaId);

        if (!existsCozinha) {
            throw new EntidadeNaoEncontradaException("Cozinha", cozinhaId, true);
        }
    }

    public void validateExistenceCidade(Restaurante restaurante) {
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
}
