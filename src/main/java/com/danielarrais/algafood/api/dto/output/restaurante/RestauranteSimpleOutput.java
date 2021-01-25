package com.danielarrais.algafood.api.dto.output.restaurante;

import com.danielarrais.algafood.api.dto.output.endereco.EnderecoOutput;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Data
public class RestauranteSimpleOutput {
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private Boolean ativo;
    private EnderecoOutput endereco;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataAtualizacao;
}
