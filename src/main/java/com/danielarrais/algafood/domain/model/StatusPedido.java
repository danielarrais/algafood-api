package com.danielarrais.algafood.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
    CRIADO("Criado"),
    CONFIRMADO("Confirmado", CRIADO),
    ENTREGUE("Entregue", CONFIRMADO),
    CANCELADO("Cancelado", CRIADO);

    private String descricao;
    private List<StatusPedido> statusAnteriores;

    StatusPedido(String descricao, StatusPedido... statusAnteriores) {
        this.descricao = descricao;
        this.statusAnteriores = Arrays.asList(statusAnteriores);
    }

    public boolean podeAlterarPara(StatusPedido statusPedido) {
        return statusPedido.statusAnteriores.contains(this);
    }

    public boolean naoPodeAlterarPara(StatusPedido statusPedido) {
        return !podeAlterarPara(statusPedido);
    }
}
