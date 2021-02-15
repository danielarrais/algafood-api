package com.danielarrais.algafood.api.dto.output.cozinha;

import com.danielarrais.algafood.api.dto.output.restaurante.RestauranteSimpleOutput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CozinhaFullOutput {

    @ApiModelProperty(value = "ID da cozinha", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome da cozinha", example = "Italiana")
    private String nome;
    private List<RestauranteSimpleOutput> restaurantes;
}