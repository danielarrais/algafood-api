package com.danielarrais.algafood.api.dto.input.formaPagamento;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoInput {
    @NotBlank
    private String descricao;
}