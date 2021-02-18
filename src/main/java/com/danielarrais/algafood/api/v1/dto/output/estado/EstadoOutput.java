package com.danielarrais.algafood.api.v1.dto.output.estado;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;


@Data
public class EstadoOutput extends RepresentationModel<EstadoOutput> {

    @ApiModelProperty(value = "ID do estado)", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome do estado)", example = "Maranhão")
    private String nome;
}