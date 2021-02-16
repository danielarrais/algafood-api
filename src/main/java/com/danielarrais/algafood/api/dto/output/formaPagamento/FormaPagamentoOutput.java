package com.danielarrais.algafood.api.dto.output.formaPagamento;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;


@Data
public class FormaPagamentoOutput extends RepresentationModel<FormaPagamentoOutput> {

    @ApiModelProperty(value = "ID da forma de pagamento", example = "1")
    private Long id;

    @ApiModelProperty(value = "Descrição da forma de pagamento", example = "Cartão de crédito")
    private String descricao;
}