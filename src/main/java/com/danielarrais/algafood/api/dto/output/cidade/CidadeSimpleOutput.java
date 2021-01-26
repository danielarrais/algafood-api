package com.danielarrais.algafood.api.dto.output.cidade;

import lombok.Data;

import java.time.OffsetDateTime;


@Data
public class CidadeSimpleOutput {
    private Long id;
    private String nome;
    private String estado;
}