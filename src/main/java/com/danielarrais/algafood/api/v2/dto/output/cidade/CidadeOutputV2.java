package com.danielarrais.algafood.api.v2.dto.output.cidade;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;


@Data
public class CidadeOutputV2 extends RepresentationModel<CidadeOutputV2> {
    @ApiModelProperty(value = "ID da cidade)", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome da cidade)", example = "Balsas")
    private String nome;

    @ApiModelProperty(value = "ID do estado)", example = "1")
    private Long idEstado;

    @ApiModelProperty(value = "Nome do estado)", example = "Maranhão")
    private String nomeEstado;
}