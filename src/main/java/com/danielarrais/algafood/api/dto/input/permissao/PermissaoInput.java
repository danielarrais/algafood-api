package com.danielarrais.algafood.api.dto.input.permissao;

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
public class PermissaoInput {
    @NotBlank
    @ApiModelProperty(required = true, example = "Cancelar Pedido")
    private String nome;

    @NotBlank
    @ApiModelProperty(required = true, example = "Permite o usuário cancelar um pedido")
    private String descricao;
}