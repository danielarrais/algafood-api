package com.danielarrais.algafood.api.dto.input.produto;

import com.danielarrais.algafood.api.dto.input.restaurante.RestauranteIdInput;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@Builder
public class ProdutoInput {
    @NotBlank
    private String descricao;

    @PositiveOrZero
    private BigDecimal preco;
    private Boolean ativo;

    @Valid
    private RestauranteIdInput restaurante;
}