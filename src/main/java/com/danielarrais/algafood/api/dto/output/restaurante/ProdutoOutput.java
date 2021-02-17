package com.danielarrais.algafood.api.dto.output.restaurante;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;


@Data
public class ProdutoOutput extends RepresentationModel<ProdutoOutput> {

    @ApiModelProperty(value = "ID do produto", example = "1")
    private Long id;

    @ApiModelProperty(value = "Descrição do produto", example = "X-Tudo com refrigerante 250ML")
    private String descricao;

    @ApiModelProperty(value = "Preço do produto", example = "15.00")
    private BigDecimal preco;

    @ApiModelProperty(value = "Status do produto", example = "false")
    private Boolean ativo = true;
}