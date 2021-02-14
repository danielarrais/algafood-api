package com.danielarrais.algafood.api.dto.input.usuario;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSenhaInput {
    @NotBlank
    @ApiModelProperty(required = true, example = "5464654654")
    private String novaSenha;

    @NotBlank
    @ApiModelProperty(required = true, example = "5464654654")
    private String senhaAtual;
}