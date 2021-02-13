package com.danielarrais.algafood.api.dto.output.cozinha;

import com.danielarrais.algafood.api.dto.output.restaurante.RestauranteSimpleOutput;
import lombok.Data;

import java.util.List;

@Data
public class CozinhaFullOutput {
    private Long id;
    private String nome;
    private List<RestauranteSimpleOutput> restaurantes;
}