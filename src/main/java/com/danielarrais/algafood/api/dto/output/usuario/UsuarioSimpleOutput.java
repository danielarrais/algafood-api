package com.danielarrais.algafood.api.dto.output.usuario;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UsuarioSimpleOutput {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataAtualizacao;
}