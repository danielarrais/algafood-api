package com.danielarrais.algafood.api.v1.dto.output.pedido;

import com.danielarrais.algafood.api.v1.dto.output.formaPagamento.FormaPagamentoOutput;
import com.danielarrais.algafood.api.v1.dto.output.restaurante.EnderecoOutput;
import com.danielarrais.algafood.api.v1.dto.output.restaurante.RestauranteSimpleOutput;
import com.danielarrais.algafood.api.v1.dto.output.usuario.UsuarioSimpleOutput;
import com.danielarrais.algafood.domain.model.StatusPedido;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
public class PedidoFullOutput extends RepresentationModel<PedidoFullOutput> {

    @ApiModelProperty(value = "Código do pedido", example = "e032d335-a030-467a-9a0c-41688ac3ea50")
    private String codigo;

    @ApiModelProperty(value = "Status do pedido", example = "CANCELADO")
    private StatusPedido status;

    @ApiModelProperty(value = "Subtotal do pedido", example = "50.00")
    private BigDecimal subtotal;

    @ApiModelProperty(value = "Taxa de frete do pedido", example = "5.00")
    private BigDecimal taxaFrete;

    @ApiModelProperty(value = "Total do pedido", example = "55.00")
    private BigDecimal valorTotal;

    @ApiModelProperty(value = "Data de criação do pedido", example = "2021-01-01T01:00:43Z")
    private OffsetDateTime dataCriacao;

    @ApiModelProperty(value = "Data de confimação do pedido", example = "2021-01-01T01:00:43Z")
    private OffsetDateTime dataConfimacao;

    @ApiModelProperty(value = "Data de entrega do pedido", example = "2021-01-01T01:00:43Z")
    private OffsetDateTime dataEntrega;

    @ApiModelProperty(value = "Data de cancelamento do pedido", example = "2021-01-01T01:00:43Z")
    private OffsetDateTime dataCancelamento;

    @ApiModelProperty(value = "Data de entrega do pedido", example = "2021-01-01T01:00:43Z")
    private EnderecoOutput enderecoEntrega;

    private UsuarioSimpleOutput cliente;
    private RestauranteSimpleOutput restaurante;
    private FormaPagamentoOutput formaPagamento;
    private Set<ItemPedidoOutput> itens;
}
