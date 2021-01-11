package com.danielarrais.algafood.util;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ValidationUtils {
    public static List<Map<String, String>> extractErrorsFrom(BindingResult bindingResult){
        var validationErrors = new ArrayList<Map<String, String>>();

        bindingResult.getFieldErrors().forEach(error -> {
            validationErrors.add(Collections.singletonMap(error.getField(), error.getDefaultMessage()));
        });

        return validationErrors;
    }
}
