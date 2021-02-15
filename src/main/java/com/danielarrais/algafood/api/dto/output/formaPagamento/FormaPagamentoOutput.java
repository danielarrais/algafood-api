package com.danielarrais.algafood.api.dto.output.formaPagamento;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class FormaPagamentoOutput {

    @ApiModelProperty(value = "ID da forma de pagamento", example = "1")
    private Long id;

    @ApiModelProperty(value = "Descrição da forma de pagamento", example = "Cartão de crédito")
    private String descricao;
}