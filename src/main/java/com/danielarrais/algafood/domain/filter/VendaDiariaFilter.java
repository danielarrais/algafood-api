package com.danielarrais.algafood.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

@Data
@FieldNameConstants
public class VendaDiariaFilter {

    @ApiModelProperty(value = "ID do restaurante", example = "1")
    private Long restauranteId;

    @ApiModelProperty(value = "Data inicial", example = "2021-01-01T01:00:43Z")
    @DateTimeFormat(iso = DATE_TIME)
    private OffsetDateTime dataCriacaoInicio;

    @ApiModelProperty(value = "Data final", example = "2021-02-01T01:00:00Z")
    @DateTimeFormat(iso = DATE_TIME)
    private OffsetDateTime dataCriacaoFim;
}
