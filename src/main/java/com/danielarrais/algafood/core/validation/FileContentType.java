package com.danielarrais.algafood.core.validation;

import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileContentTypeValidator.class})
public @interface FileContentType {
    String message() default "Tipo do arquivo inválido";

    Class<?>[] groups() default {};

    Class<? extends javax.validation.Payload>[] payload() default {};

    String[] allowed() ;
}