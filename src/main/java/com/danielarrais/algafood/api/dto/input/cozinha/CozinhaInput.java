package com.danielarrais.algafood.api.dto.input.cozinha;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CozinhaInput {
    @NotBlank
    private String nome;
}