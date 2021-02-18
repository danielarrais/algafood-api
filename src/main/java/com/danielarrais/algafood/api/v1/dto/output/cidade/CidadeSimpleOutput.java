package com.danielarrais.algafood.api.v1.dto.output.cidade;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;


@Data
public class CidadeSimpleOutput extends RepresentationModel<CidadeSimpleOutput> {

    @ApiModelProperty(value = "ID da cidade", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome da cidade", example = "1")
    private String nome;

    @ApiModelProperty(value = "Nome do estado da cidade", example = "1")
    private String estado;
}