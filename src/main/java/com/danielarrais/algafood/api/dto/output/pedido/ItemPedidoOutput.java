package com.danielarrais.algafood.api.dto.output.pedido;

import com.danielarrais.algafood.api.dto.output.restaurante.ProdutoOutput;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPedidoOutput {
    private Long id;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private String observacao;
    private ProdutoOutput produto;
}
