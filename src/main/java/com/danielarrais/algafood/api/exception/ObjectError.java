package com.danielarrais.algafood.api.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("Erro")
@AllArgsConstructor
public class ObjectError {
    @ApiModelProperty(example = "nome")
    private String name;

    @ApiModelProperty(example = "O nome da permissão é obrigatório")
    private String message;
}
