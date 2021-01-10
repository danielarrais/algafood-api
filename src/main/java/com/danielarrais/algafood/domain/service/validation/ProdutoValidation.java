package com.danielarrais.algafood.domain.service.validation;

import com.danielarrais.algafood.domain.exception.DependenciaNaoEncontradaException;
import com.danielarrais.algafood.domain.model.Produto;
import com.danielarrais.algafood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Component;

@Component
public class ProdutoValidation {
    private final RestauranteRepository restauranteRepository;

    public ProdutoValidation(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public void validateExistenceRestaurante(Produto restaurante) {
        Long restauranteId = restaurante.getRestaurante().getId();
        boolean existsRestaurante = restauranteRepository.existsById(restauranteId);

        if (!existsRestaurante) {
            throw new DependenciaNaoEncontradaException("Restaurante", restauranteId);
        }
    }
}
