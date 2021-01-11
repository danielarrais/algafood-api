package com.danielarrais.algafood.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationProblem extends Problem {
    private List<Map<String, String>> errors;
}
