package com.danielarrais.algafood.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class ValidationUtils {
    private static ResourceBundleMessageSource messageSource;

    public ValidationUtils(ResourceBundleMessageSource messageSource) {
        ValidationUtils.messageSource = messageSource;
    }

    public static List<Map<String, String>> extractErrorsFrom(BindingResult bindingResult){
        var validationErrors = new ArrayList<Map<String, String>>();

        bindingResult.getAllErrors().forEach(error -> {
            String name = error instanceof FieldError ?
                    ((FieldError) error).getField() : error.getObjectName();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            validationErrors.add(Collections.singletonMap(name, message));
        });

        return validationErrors;
    }
}
