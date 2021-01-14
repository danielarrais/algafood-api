package com.danielarrais.algafood.core.validation;

import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FreteGratisValidator.class})
public @interface FreteGratis {
    String message() default "{1} deve conter {2}";
    Class<?>[] groups() default {};
    Class<? extends javax.validation.Payload>[] payload() default {};

    String valorField();
    String descricaoField();
    String descricaoObrigatoria();
}
