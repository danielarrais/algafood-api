package com.danielarrais.algafood.api.dto.output.usuario;

import lombok.Data;

@Data
public class UsuarioSimpleOutput {
    private Long id;
    private String nome;
    private String email;
}