package com.danielarrais.algafood.api.v1.dto.output.cidade;

import com.danielarrais.algafood.api.v1.dto.output.estado.EstadoOutput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;


@Data
public class CidadeOutput extends RepresentationModel<CidadeOutput> {
    @ApiModelProperty(value = "ID da cidade)", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome da cidade)", example = "Balsas")
    private String nome;

    private EstadoOutput estado;
}