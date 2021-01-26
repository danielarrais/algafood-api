package com.danielarrais.algafood.api.dto.output.pedido;

import com.danielarrais.algafood.api.dto.output.formaPagamento.FormaPagamentoOutput;
import com.danielarrais.algafood.api.dto.output.restaurante.EnderecoOutput;
import com.danielarrais.algafood.api.dto.output.restaurante.RestauranteSimpleOutput;
import com.danielarrais.algafood.api.dto.output.usuario.UsuarioSimpleOutput;
import com.danielarrais.algafood.domain.model.StatusPedido;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
public class PedidoFullOutput {
    private Long id;
    private StatusPedido status;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfimacao;
    private OffsetDateTime dataEntrega;
    private OffsetDateTime dataCancelamento;
    private EnderecoOutput enderecoEntrega;
    private UsuarioSimpleOutput cliente;
    private RestauranteSimpleOutput restaurante;
    private FormaPagamentoOutput formaPagamento;
    private Set<ItemPedidoOutput> itens;
}
