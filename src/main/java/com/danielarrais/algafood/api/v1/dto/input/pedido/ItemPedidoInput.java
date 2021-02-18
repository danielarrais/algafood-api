package com.danielarrais.algafood.api.v1.dto.input.pedido;

import com.danielarrais.algafood.api.v1.dto.input.restaurante.ProdutoIdInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class ItemPedidoInput {

    @NotNull
    @PositiveOrZero
    @ApiModelProperty(required = true, example = "5")
    private Integer quantidade;
    @ApiModelProperty(example = "Retirar os ovos de dois dos hamburgures")
    private String observacao;

    @Valid
    @NotNull
    private ProdutoIdInput produto;
}