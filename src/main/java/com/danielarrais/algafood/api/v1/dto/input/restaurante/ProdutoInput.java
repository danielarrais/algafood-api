package com.danielarrais.algafood.api.v1.dto.input.restaurante;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(required = true, example = "X-Tudo com refrigerante")
    private String descricao;

    @PositiveOrZero
    @ApiModelProperty(required = true, example = "30.5")
    private BigDecimal preco;

    @ApiModelProperty(example = "false")
    private Boolean ativo;
}