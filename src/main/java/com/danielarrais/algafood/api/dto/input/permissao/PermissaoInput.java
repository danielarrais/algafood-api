package com.danielarrais.algafood.api.dto.input.permissao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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