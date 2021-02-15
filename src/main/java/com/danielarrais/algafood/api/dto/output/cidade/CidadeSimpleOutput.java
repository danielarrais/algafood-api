package com.danielarrais.algafood.api.dto.output.cidade;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class CidadeSimpleOutput {

    @ApiModelProperty(value = "ID da cidade", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome da cidade", example = "1")
    private String nome;

    @ApiModelProperty(value = "Nome do estado da cidade", example = "1")
    private String estado;
}