package com.danielarrais.algafood.api.dto.input.formaPagamento;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoInput {
    @NotBlank
    @ApiModelProperty(required = true, example = "Cartão de Débito")
    private String descricao;
}