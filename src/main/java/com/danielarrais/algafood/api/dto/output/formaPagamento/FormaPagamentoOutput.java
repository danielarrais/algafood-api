package com.danielarrais.algafood.api.dto.output.formaPagamento;

import lombok.Data;

import java.time.OffsetDateTime;


@Data
public class FormaPagamentoOutput {
    private Long id;
    private String descricao;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataAtualizacao;
}