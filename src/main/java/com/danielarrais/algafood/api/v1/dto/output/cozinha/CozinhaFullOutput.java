package com.danielarrais.algafood.api.v1.dto.output.cozinha;

import com.danielarrais.algafood.api.v1.dto.output.restaurante.RestauranteSimpleOutput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
public class CozinhaFullOutput extends RepresentationModel<CozinhaFullOutput> {

    @ApiModelProperty(value = "ID da cozinha", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome da cozinha", example = "Italiana")
    private String nome;
    private List<RestauranteSimpleOutput> restaurantes;
}