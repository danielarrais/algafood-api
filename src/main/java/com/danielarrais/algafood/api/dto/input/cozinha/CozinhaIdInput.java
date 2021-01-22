package com.danielarrais.algafood.api.dto.input.cozinha;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class CozinhaIdInput {
    @NotNull
    private Long id;
}