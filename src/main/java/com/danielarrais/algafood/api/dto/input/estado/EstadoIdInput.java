package com.danielarrais.algafood.api.dto.input.estado;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoIdInput {
    @NotNull
    @ApiModelProperty(required = true, example = "1")
    private Long id;
}