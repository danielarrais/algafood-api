package com.danielarrais.algafood.api.dto.output.cidade;

import com.danielarrais.algafood.api.dto.output.estado.EstadoOutput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("Cidade")
public class CidadeOutput {
    @ApiModelProperty(value = "ID da cidade)", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome da cidade)", example = "Balsas")
    private String nome;

    private EstadoOutput estado;
}