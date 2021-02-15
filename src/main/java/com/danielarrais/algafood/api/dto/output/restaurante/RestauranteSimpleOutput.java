package com.danielarrais.algafood.api.dto.output.restaurante;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class RestauranteSimpleOutput {

    @ApiModelProperty(value = "ID do restaurante", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome do restaurante", example = "Pé de fava")
    private String nome;

    @ApiModelProperty(value = "Taxa de entrega do restaurante", example = "10.00")
    private BigDecimal taxaFrete;

    @ApiModelProperty(value = "Status do restaurante", example = "true")
    private Boolean ativo;
}
