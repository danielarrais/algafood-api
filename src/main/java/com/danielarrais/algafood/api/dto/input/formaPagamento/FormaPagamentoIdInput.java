package com.danielarrais.algafood.api.dto.input.formaPagamento;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class FormaPagamentoIdInput {
    @NotNull
    private Long id;
}