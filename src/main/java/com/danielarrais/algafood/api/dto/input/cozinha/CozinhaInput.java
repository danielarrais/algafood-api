package com.danielarrais.algafood.api.dto.input.cozinha;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CozinhaInput {
    @NotBlank
    private String nome;
}