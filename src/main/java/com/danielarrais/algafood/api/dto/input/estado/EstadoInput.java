package com.danielarrais.algafood.api.dto.input.estado;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoInput {
    @NotBlank
    private String nome;
}