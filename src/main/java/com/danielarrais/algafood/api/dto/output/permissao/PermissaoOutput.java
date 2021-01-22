package com.danielarrais.algafood.api.dto.output.permissao;

import lombok.Data;

import java.time.OffsetDateTime;


@Data
public class PermissaoOutput {
    private Long id;
    private String nome;
    private String descricao;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataAtualizacao;
}