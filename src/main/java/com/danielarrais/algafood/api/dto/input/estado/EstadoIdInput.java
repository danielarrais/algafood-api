package com.danielarrais.algafood.api.dto.input.estado;

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
    private Long id;
}