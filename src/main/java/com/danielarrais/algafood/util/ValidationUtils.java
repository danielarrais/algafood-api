package com.danielarrais.algafood.util;

import com.danielarrais.algafood.api.exception.handler.ObjectError;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;

@Component
public class ValidationUtils {
    private static ResourceBundleMessageSource messageSource;

    public ValidationUtils(ResourceBundleMessageSource messageSource) {
        ValidationUtils.messageSource = messageSource;
    }

    public static List<ObjectError> extractErrorsFrom(BindingResult bindingResult) {
        var validationErrors = new ArrayList<ObjectError>();

        bindingResult.getAllErrors().forEach(error -> {
            var name = error instanceof FieldError ? ((FieldError) error).getField() : error.getObjectName();
            var message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            validationErrors.add(new ObjectError(name, message));
        });

        validationErrors.sort(Comparator.comparing(ObjectError::getName));

        return validationErrors;
    }
}
