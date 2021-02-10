package com.danielarrais.algafood.api.controller;

import com.danielarrais.algafood.domain.filter.VendaDiariaFilter;
import com.danielarrais.algafood.domain.model.dto.VendaDiaria;
import com.danielarrais.algafood.domain.service.ConsultaVendaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {
    private final ConsultaVendaService consultaVendaService;

    public EstatisticasController(ConsultaVendaService consultaVendaService) {
        this.consultaVendaService = consultaVendaService;
    }

    @GetMapping("/vendas-diarias")
    public List<VendaDiaria> findVendasDiarias(VendaDiariaFilter filter) {
        return consultaVendaService.consultarVendasDiarias(filter);
    }
}