package com.danielarrais.algafood.api.dto.output.pedido;

import com.danielarrais.algafood.api.dto.output.restaurante.RestauranteSimpleOutput;
import com.danielarrais.algafood.api.dto.output.usuario.UsuarioSimpleOutput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class PedidoSimpleOutput {

    @ApiModelProperty(value = "Código do pedido", example = "e032d335-a030-467a-9a0c-41688ac3ea50")
    private String codigo;

    @ApiModelProperty(value = "Subtotal do pedido", example = "50.00")
    private BigDecimal subtotal;

    @ApiModelProperty(value = "Taxa de frete do pedido", example = "5.00")
    private BigDecimal taxaFrete;

    @ApiModelProperty(value = "Total do pedido", example = "55.00")
    private BigDecimal valorTotal;

    @ApiModelProperty(value = "Status do pedido", example = "CANCELADO")
    private String status;

    @ApiModelProperty(value = "Data de criação do pedido", example = "2021-01-01T01:00:43Z")
    private OffsetDateTime dataCriacao;

    private RestauranteSimpleOutput restaurante;
    private UsuarioSimpleOutput cliente;

    @ApiModelProperty(value = "Nome do cliente", example = "Daniel Arrais")
    private String nomeUsuario;
}
