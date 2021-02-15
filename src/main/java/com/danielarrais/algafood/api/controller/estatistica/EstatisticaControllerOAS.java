package com.danielarrais.algafood.api.controller.estatistica;

import com.danielarrais.algafood.domain.filter.VendaDiariaFilter;
import com.danielarrais.algafood.domain.model.dto.VendaDiaria;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Api(tags = "Estatisticas")
interface EstatisticaControllerOAS {

    @GetMapping(value = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    List<VendaDiaria> findVendasDiariasInJSON(VendaDiariaFilter filter);

    @GetMapping(value = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<byte[]> findVendasDiariasInPDF(VendaDiariaFilter filter);
}