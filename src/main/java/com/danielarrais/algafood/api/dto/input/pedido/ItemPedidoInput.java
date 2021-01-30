package com.danielarrais.algafood.api.dto.input.pedido;

import com.danielarrais.algafood.api.dto.input.restaurante.ProdutoIdInput;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class ItemPedidoInput {

    @NotNull
    @PositiveOrZero
    private Integer quantidade;
    private String observacao;

    @Valid
    @NotNull
    private ProdutoIdInput produto;
}