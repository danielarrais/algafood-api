package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {
    byte[] emitirVendasDiarias(VendaDiariaFilter filter);
}
