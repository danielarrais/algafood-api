package com.danielarrais.algafood.domain.model.dto;

import lombok.Data;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class VendaDiaria {
    private Date data;
    private Long totalVendas;
    private BigDecimal totalFaturado;

    @SneakyThrows
    public VendaDiaria(String data, Long totalVendas, BigDecimal totalFaturado) {
        this.data = new SimpleDateFormat("yyyy-MM-dd").parse(data);
        this.totalVendas = totalVendas;
        this.totalFaturado = totalFaturado;
    }
}
