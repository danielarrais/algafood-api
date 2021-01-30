package com.danielarrais.algafood.api.dto.output.restaurante;

import com.danielarrais.algafood.api.dto.output.cozinha.CozinhaOutput;
import com.danielarrais.algafood.api.dto.output.formaPagamento.FormaPagamentoOutput;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;


@Data
public class RestauranteFullOutput {
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private Boolean ativo;
    private CozinhaOutput cozinha;
    private List<FormaPagamentoOutput> formasPagamento;
    private EnderecoOutput endereco;
}
