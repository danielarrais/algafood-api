package com.danielarrais.algafood.api.exception;

import com.danielarrais.algafood.api.exception.handler.ObjectError;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationProblem extends Problem {
    private List<ObjectError> errors;
}
