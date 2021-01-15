package com.danielarrais.algafood.core.validation;

import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MultiploValidator.class})
public @interface Multiplo {
    String message() default "{0} não é múltiplo de {numero}";
    Class<?>[] groups() default {};
    Class<? extends javax.validation.Payload>[] payload() default {};

    int numero();
}
