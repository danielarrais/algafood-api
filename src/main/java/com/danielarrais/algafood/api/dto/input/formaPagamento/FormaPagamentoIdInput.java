package com.danielarrais.algafood.api.dto.input.formaPagamento;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoIdInput {
    @NotNull
    private Long id;
}