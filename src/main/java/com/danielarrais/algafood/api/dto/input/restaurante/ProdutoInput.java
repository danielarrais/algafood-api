package com.danielarrais.algafood.api.dto.input.restaurante;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoInput {
    @NotBlank
    private String descricao;

    @PositiveOrZero
    private BigDecimal preco;
    private Boolean ativo;
}