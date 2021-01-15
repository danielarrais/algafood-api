package com.danielarrais.algafood.domain.exception;

import lombok.Getter;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

@Getter
public class SmartValidationException extends BindException {
    public SmartValidationException(BindingResult bindingResult) {
        super(bindingResult);
    }
}
