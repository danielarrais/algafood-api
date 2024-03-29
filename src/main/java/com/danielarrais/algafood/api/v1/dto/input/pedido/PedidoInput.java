package com.danielarrais.algafood.api.v1.dto.input.pedido;

import com.danielarrais.algafood.api.v1.dto.input.endereco.EnderecoInput;
import com.danielarrais.algafood.api.v1.dto.input.formaPagamento.FormaPagamentoIdInput;
import com.danielarrais.algafood.api.v1.dto.input.restaurante.RestauranteIdInput;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class PedidoInput {
    @Valid
    @NotNull
    private EnderecoInput enderecoEntrega;

    @Valid
    @NotNull
    private RestauranteIdInput restaurante;

    @Valid
    @NotNull
    private FormaPagamentoIdInput formaPagamento;

    @NotEmpty
    private Set<@Valid ItemPedidoInput> itens;
}