package com.danielarrais.algafood.api.dto.input.restaurante;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class RestauranteIdInput {
    @NotNull
    private Long id;
}
