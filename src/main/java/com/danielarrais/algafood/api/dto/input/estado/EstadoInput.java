package com.danielarrais.algafood.api.dto.input.estado;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class EstadoInput {
    @NotBlank
    private String nome;
}