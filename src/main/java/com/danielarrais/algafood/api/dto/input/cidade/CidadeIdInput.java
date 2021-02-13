package com.danielarrais.algafood.api.dto.input.cidade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CidadeIdInput {
    @NotNull
    private Long id;
}