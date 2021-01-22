package com.danielarrais.algafood.api.dto.input.permissao;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissaoInput {
    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;
}