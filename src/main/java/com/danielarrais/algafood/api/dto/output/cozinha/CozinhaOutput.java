package com.danielarrais.algafood.api.dto.output.cozinha;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;


@Data
public class CozinhaOutput extends RepresentationModel<CozinhaOutput> {

    @ApiModelProperty(value = "ID da cozinha", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome da cozinha", example = "Italiana")
    private String nome;
}