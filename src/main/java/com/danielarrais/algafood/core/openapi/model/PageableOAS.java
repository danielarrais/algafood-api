package com.danielarrais.algafood.core.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("Pageable")
public class PageableOAS {
    @ApiModelProperty(example = "0", value = "Número da página (começa em 0)")
    private int page;

    @ApiModelProperty(example = "1", value = "Quantidade de elementos por página")
    private int size;

    @ApiModelProperty(example = "nome,ASC", value = "Nome da propriedade para ordenação")
    private List<String> sort;
}
