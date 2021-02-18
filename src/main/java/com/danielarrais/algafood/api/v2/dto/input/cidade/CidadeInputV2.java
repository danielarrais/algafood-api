package com.danielarrais.algafood.api.v2.dto.input.cidade;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CidadeInputV2 {
    @NotBlank
    @ApiModelProperty(required = true, example = "Sambaíba")
    private String nome;

    @NotNull
    @ApiModelProperty(required = true, example = "1")
    private Long estadoId;
}