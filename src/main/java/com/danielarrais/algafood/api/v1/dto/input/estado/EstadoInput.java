package com.danielarrais.algafood.api.v1.dto.input.estado;

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
public class EstadoInput {
    @NotBlank
    @ApiModelProperty(required = true, example = "Maranhão")
    private String nome;
}