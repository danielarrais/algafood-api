package com.danielarrais.algafood.api.dto.input.formaPagamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoIdInput {
    @NotNull
    private Long id;
}