package com.danielarrais.algafood.core.openapi.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PageOAS<T> {
    List<T> content;

    @ApiModelProperty(example = "10", value = "Quantidade de registros por página")
    Integer size;

    @ApiModelProperty(example = "1", value = "Total de registros")
    Integer totalElements;

    @ApiModelProperty(example = "1", value = "Total de registros da página")
    Integer totalPages;

    @ApiModelProperty(example = "1", value = "Número da página (começa em 0)")
    Integer page;
}
