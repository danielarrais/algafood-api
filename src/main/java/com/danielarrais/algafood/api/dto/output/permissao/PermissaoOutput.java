package com.danielarrais.algafood.api.dto.output.permissao;

import lombok.Data;


@Data
public class PermissaoOutput {
    private Long id;
    private String nome;
    private String descricao;
}