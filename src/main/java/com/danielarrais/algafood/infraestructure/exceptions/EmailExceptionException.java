package com.danielarrais.algafood.infraestructure.exceptions;

public class EmailExceptionException extends RuntimeException {
    public EmailExceptionException(String message, Throwable cause) {
        super(message, cause);
    }
}

