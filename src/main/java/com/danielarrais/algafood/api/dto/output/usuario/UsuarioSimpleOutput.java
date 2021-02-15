package com.danielarrais.algafood.api.dto.output.usuario;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UsuarioSimpleOutput {

    @ApiModelProperty(value = "ID do usuário", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome do usuário", example = "Lucas Silva")
    private String nome;

    @ApiModelProperty(value = "E-mail do usuário", example = "lucassilva@gmail.com")
    private String email;
}