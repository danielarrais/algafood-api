package com.danielarrais.algafood.api.dto.input.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSenhaInput {
    @NotBlank
    private String novaSenha;

    @NotBlank
    private String senhaAtual;
}