package com.danielarrais.algafood.api.v1.dto.output.restaurante;

import com.danielarrais.algafood.api.v1.dto.output.cozinha.CozinhaOutput;
import com.danielarrais.algafood.api.v1.dto.output.formaPagamento.FormaPagamentoOutput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RestauranteFullOutput  extends RepresentationModel<RestauranteFullOutput> {

    @ApiModelProperty(value = "ID do restaurante", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome do restaurante", example = "Pé de fava")
    private String nome;

    @ApiModelProperty(value = "Taxa de entrega do restaurante", example = "10.00")
    private BigDecimal taxaFrete;

    @ApiModelProperty(value = "Status do restaurante", example = "true")
    private Boolean ativo;

    private CozinhaOutput cozinha;
    private List<FormaPagamentoOutput> formasPagamento;
    private EnderecoOutput endereco;
}
