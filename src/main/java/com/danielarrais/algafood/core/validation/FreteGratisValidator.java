package com.danielarrais.algafood.core.validation;

import com.danielarrais.algafood.util.CustomBeansUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public class FreteGratisValidator implements ConstraintValidator<FreteGratis, Object> {
    private String valorField;
    private String descricaoField;
    private String descricaoObrigatoria;

    @Override
    public void initialize(FreteGratis constraintAnnotation) {
        this.descricaoField = constraintAnnotation.descricaoField();
        this.valorField = constraintAnnotation.valorField();
        this.descricaoObrigatoria = constraintAnnotation.descricaoObrigatoria();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        try {
            BigDecimal valor = CustomBeansUtils.getPropertieValue(value, valorField, BigDecimal.class);
            String descricao = CustomBeansUtils.getPropertieValue(value, descricaoField, String.class);

            if (valor != null && ZERO.compareTo(valor) == 0 && StringUtils.hasText(descricao)) {
                return descricao.toLowerCase().contains(descricaoObrigatoria);
            }

        } catch (Exception e) {
            throw new ValidationException(e);
        }

        return false;
    }
}
