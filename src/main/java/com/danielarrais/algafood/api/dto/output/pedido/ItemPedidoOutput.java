package com.danielarrais.algafood.api.dto.output.pedido;

import com.danielarrais.algafood.api.dto.output.restaurante.ProdutoOutput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Data
public class ItemPedidoOutput extends RepresentationModel<ItemPedidoOutput> {

    @ApiModelProperty(value = "Código do pedido", example = "e032d335-a030-467a-9a0c-41688ac3ea50")
    private String codigo;

    @ApiModelProperty(value = "Quantidade de itens", example = "10")
    private Integer quantidade;

    @ApiModelProperty(value = "Preço unitário do item", example = "5.00")
    private BigDecimal precoUnitario;

    @ApiModelProperty(value = "Preço total dos itens", example = "50.00")
    private BigDecimal precoTotal;

    @ApiModelProperty(value = "Obeservação do cliente", example = "Retirar ovos")
    private String observacao;

    private ProdutoOutput produto;
}
