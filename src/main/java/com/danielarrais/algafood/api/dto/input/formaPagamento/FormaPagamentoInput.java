package com.danielarrais.algafood.api.dto.input.formaPagamento;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class FormaPagamentoInput {
    @NotBlank
    private String descricao;
}