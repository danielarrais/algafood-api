package com.danielarrais.algafood.api.dto.output.restaurante;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class RestauranteSimpleOutput {
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private Boolean ativo;
}
