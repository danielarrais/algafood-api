package com.danielarrais.algafood.api.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObjectError {
    private String name;
    private String message;
}
