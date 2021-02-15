package com.danielarrais.algafood.api.exception;

import com.danielarrais.algafood.util.serializer.HttpStatusSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Problema")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {
    @JsonSerialize(converter = HttpStatusSerializer.class)
    @ApiModelProperty(example = "400 - Bad Request")
    private HttpStatus status;

    @ApiModelProperty(hidden = true)
    private String type;

    @ApiModelProperty(example = "Erros de validação")
    private String title;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos")
    private String detail;

    @ApiModelProperty(position = 5)
    private List<ObjectError> errors;
}
