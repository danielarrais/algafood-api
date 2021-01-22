package com.danielarrais.algafood.api.dto.input.cidade;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class CidadeIdInput {
    @NotNull
    private Long id;
}