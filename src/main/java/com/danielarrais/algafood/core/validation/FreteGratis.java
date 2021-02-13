package com.danielarrais.algafood.core.validation;

import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FreteGratisValidator.class})
public @interface FreteGratis {
    String message() default "O {0} deve conter \"{1}\"";

    Class<?>[] groups() default {};

    Class<? extends javax.validation.Payload>[] payload() default {};

    String valorField();

    String descricaoField();

    String descricaoObrigatoria();
}
