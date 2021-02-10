package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.filter.VendaDiariaFilter;
import com.danielarrais.algafood.domain.model.dto.VendaDiaria;

import java.util.List;

public interface ConsultaVendaService {
    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter);
}
