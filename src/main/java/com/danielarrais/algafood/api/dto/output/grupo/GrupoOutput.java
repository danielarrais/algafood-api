package com.danielarrais.algafood.api.dto.output.grupo;

import com.danielarrais.algafood.api.dto.output.permissao.PermissaoOutput;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;


@Data
public class GrupoOutput {
    private Long id;
    private String nome;
    private List<PermissaoOutput> permissoes;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataAtualizacao;
}