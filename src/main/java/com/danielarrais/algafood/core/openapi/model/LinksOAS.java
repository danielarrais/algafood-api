package com.danielarrais.algafood.core.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Links")
public class LinksOAS {

    @ApiModelProperty(example = "self", position = 0)
    private String rel;

    @ApiModelProperty(example = "http://localhost:8080/estados/1", position = 1)
    private String href;
}
