package com.danielarrais.algafood.api.dto.input.estado;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class EstadoIdInput {
    @NotNull
    private Long id;
}