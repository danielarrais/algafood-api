package com.danielarrais.algafood.api.dto.output.estado;

import lombok.Data;

import java.time.OffsetDateTime;


@Data
public class EstadoOutput {
    private Long id;
    private String nome;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataAtualizacao;
}