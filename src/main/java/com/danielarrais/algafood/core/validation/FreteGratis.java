package com.danielarrais.algafood.core.validation;

import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FreteGratisValidator.class})
public @interface FreteGratis {
    String message() default "{0} não tem frete grátis";
    Class<?>[] groups() default {};
    Class<? extends javax.validation.Payload>[] payload() default {};

    String valorField();
    String descricaoField();
    String descricaoObrigatoria();
}
