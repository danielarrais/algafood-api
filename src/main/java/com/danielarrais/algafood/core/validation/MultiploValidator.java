package com.danielarrais.algafood.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.Objects;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number> {
    private int multiplo;

    @Override
    public void initialize(Multiplo constraintAnnotation) {
        this.multiplo = constraintAnnotation.numero();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.nonNull(value)) {
            var valorDecimal = BigDecimal.valueOf(value.doubleValue());
            var multiploDecimal = BigDecimal.valueOf(this.multiplo);
            var resto = valorDecimal.remainder(multiploDecimal);

            return BigDecimal.ZERO.compareTo(resto) == 0;
        }

        return false;
    }
}
