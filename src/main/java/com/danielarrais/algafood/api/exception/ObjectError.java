package com.danielarrais.algafood.api.exception;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel("Erro")
@AllArgsConstructor
public class ObjectError {
    private String name;
    private String message;
}
