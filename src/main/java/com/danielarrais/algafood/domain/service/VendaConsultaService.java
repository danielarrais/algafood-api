package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.filter.VendaDiariaFilter;
import com.danielarrais.algafood.domain.model.dto.VendaDiaria;

import java.util.List;

public interface VendaConsultaService {
    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter);
}
