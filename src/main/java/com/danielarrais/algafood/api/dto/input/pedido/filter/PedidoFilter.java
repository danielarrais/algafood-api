package com.danielarrais.algafood.api.dto.input.pedido.filter;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

@Data
public class PedidoFilter {
    private Long clienteId;
    private Long restauranteId;

    @DateTimeFormat(iso = DATE_TIME)
    private OffsetDateTime dataCriacaoInicio;

    @DateTimeFormat(iso = DATE_TIME)
    private OffsetDateTime dataCriacaoFim;
}
