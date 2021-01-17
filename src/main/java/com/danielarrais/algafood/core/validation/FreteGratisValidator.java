package com.danielarrais.algafood.core.validation;

import com.danielarrais.algafood.util.CustomBeansUtils;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public class FreteGratisValidator implements ConstraintValidator<FreteGratis, Object> {
    private String message;
    private String valorField;
    private String descricaoField;
    private String descricaoObrigatoria;

    @Override
    public void initialize(FreteGratis constraintAnnotation) {
        this.message = constraintAnnotation.message();
        this.descricaoField = constraintAnnotation.descricaoField();
        this.valorField = constraintAnnotation.valorField();
        this.descricaoObrigatoria = constraintAnnotation.descricaoObrigatoria();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        var valido = false;

        try {
            BigDecimal valor = CustomBeansUtils.getPropertieValue(value, valorField);
            String descricao = CustomBeansUtils.getPropertieValue(value, descricaoField);

            if (valor != null && ZERO.compareTo(valor) == 0 && StringUtils.hasText(descricao)) {
                valido = descricao.toLowerCase().contains(descricaoObrigatoria);
            }

            if (!valido) {
                tratarTemplateDaMensagem(context);
            }

        } catch (Exception e) {
            throw new ValidationException(e);
        }

        return valido;
    }

    public void tratarTemplateDaMensagem(ConstraintValidatorContext context){
        HibernateConstraintValidatorContext hibernateContext = context.unwrap(HibernateConstraintValidatorContext.class);

        hibernateContext.disableDefaultConstraintViolation();
        hibernateContext.addMessageParameter("0", descricaoField)
                .addMessageParameter("1", descricaoObrigatoria)
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
