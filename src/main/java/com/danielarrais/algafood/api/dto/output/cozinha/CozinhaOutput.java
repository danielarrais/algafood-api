package com.danielarrais.algafood.api.dto.output.cozinha;

import com.danielarrais.algafood.api.dto.output.restaurante.RestauranteOutput;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;


@Data
public class CozinhaOutput {
    private Long id;
    private String nome;
    private List<RestauranteOutput> restaurantes;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataAtualizacao;
}