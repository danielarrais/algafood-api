package com.danielarrais.algafood.api.dto.input.cozinha;

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
public class CozinhaInput {
    @NotBlank
    @ApiModelProperty(required = true, example = "Italiana")
    private String nome;
}