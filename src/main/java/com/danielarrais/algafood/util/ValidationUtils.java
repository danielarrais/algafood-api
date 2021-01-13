package com.danielarrais.algafood.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ValidationUtils {
    public static List<Map<String, String>> extractErrorsFrom(BindingResult bindingResult){
        var validationErrors = new ArrayList<Map<String, String>>();
        var messageSource = new ResourceBundleMessageSource();

        messageSource.setBasenames("messages");
        messageSource.setUseCodeAsDefaultMessage(true);

        bindingResult.getFieldErrors().forEach(error -> {
            var objectNameKebabCase = StringUtils.camelToKebab(error.getObjectName());

            messageSource.setBasenames(String.format("messages/%s", objectNameKebabCase));

            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            validationErrors.add(Collections.singletonMap(error.getField(), message));
        });

        return validationErrors;
    }
}
