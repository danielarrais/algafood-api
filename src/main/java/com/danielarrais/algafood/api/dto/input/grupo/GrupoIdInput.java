package com.danielarrais.algafood.api.dto.input.grupo;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class GrupoIdInput {
    @NotNull
    private Long id;
}