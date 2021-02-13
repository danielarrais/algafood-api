package com.danielarrais.algafood.api.dto.output.restaurante;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProdutoOutput {
    private Long id;
    private String descricao;
    private BigDecimal preco;
    private Boolean ativo = true;
}