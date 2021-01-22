package com.danielarrais.algafood.api.dto.input.cozinha;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CozinhaIdInput {
    @NotNull
    private Long id;
}