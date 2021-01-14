package com.danielarrais.algafood.domain.service.validation;

import com.danielarrais.algafood.domain.exception.SmartValidationException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;

@Service
public class ComumValidation {
    @Autowired
    private SmartValidator smartValidator;

    @SneakyThrows
    public void smartValidate(Object object) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(object, object.getClass().getSimpleName());

        smartValidator.validate(object, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new SmartValidationException(bindingResult);
        }
    }
}
