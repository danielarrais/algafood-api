package com.danielarrais.algafood.domain.event;

import com.danielarrais.algafood.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PedidoCanceladoEvent {
    private Pedido pedido;
}
