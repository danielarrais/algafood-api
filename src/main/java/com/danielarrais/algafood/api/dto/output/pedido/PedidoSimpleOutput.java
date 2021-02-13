package com.danielarrais.algafood.api.dto.output.pedido;

import com.danielarrais.algafood.api.dto.output.restaurante.RestauranteSimpleOutput;
import com.danielarrais.algafood.api.dto.output.usuario.UsuarioSimpleOutput;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class PedidoSimpleOutput {
    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private String status;
    private OffsetDateTime dataCriacao;
    private RestauranteSimpleOutput restaurante;
    private UsuarioSimpleOutput cliente;
    private String nomeUsuario;
}
