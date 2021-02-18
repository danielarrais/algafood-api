package com.danielarrais.algafood.api.v1.controller.estatistica;

import com.danielarrais.algafood.core.util.MediaTypes;
import com.danielarrais.algafood.domain.filter.VendaDiariaFilter;
import com.danielarrais.algafood.domain.model.dto.VendaDiaria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Api(tags = "Estatisticas")
interface EstatisticaControllerOAS {

    @ApiOperation("Retorna vendas diárias")
    @GetMapping(value = "/vendas-diarias", produces = MediaTypes.MULTIPART_ALGAFOOD_V1)
    List<VendaDiaria> findVendasDiariasInJSON(@ApiParam("Filtros") VendaDiariaFilter filter);

    @ApiOperation("Retorna PDF com venda diárias")
    @GetMapping(value = "/vendas-diarias", produces = MediaTypes.PDF_ALGAFOOD_V1)
    ResponseEntity<byte[]> findVendasDiariasInPDF(@ApiParam("Filtros") VendaDiariaFilter filter);
}