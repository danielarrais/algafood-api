package com.danielarrais.algafood.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class VendaDiaria {
    private String data;
    private Long totalVendas;
    private BigDecimal totalFaturado;
}
