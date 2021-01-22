package com.danielarrais.algafood.api.dto.input.restaurante;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteIdInput {
    @NotNull
    private Long id;
}
