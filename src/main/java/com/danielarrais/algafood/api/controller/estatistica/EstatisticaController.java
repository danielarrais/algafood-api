package com.danielarrais.algafood.api.controller.estatistica;

import com.danielarrais.algafood.domain.filter.VendaDiariaFilter;
import com.danielarrais.algafood.domain.model.dto.VendaDiaria;
import com.danielarrais.algafood.domain.service.VendaConsultaService;
import com.danielarrais.algafood.domain.service.VendaReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticaController {
    private final VendaConsultaService vendaConsultaService;
    private final VendaReportService vendaReportService;

    public EstatisticaController(VendaConsultaService vendaConsultaService, VendaReportService vendaReportService) {
        this.vendaConsultaService = vendaConsultaService;
        this.vendaReportService = vendaReportService;
    }

    @GetMapping(value = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VendaDiaria> findVendasDiariasInJSON(VendaDiariaFilter filter) {
        return vendaConsultaService.consultarVendasDiarias(filter);
    }

    @GetMapping(value = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> findVendasDiariasInPDF(VendaDiariaFilter filter) {
        byte[] pdf = vendaReportService.emitirVendasDiarias(filter);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas_diarias.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}