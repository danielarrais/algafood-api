package com.danielarrais.algafood.infraestructure.exceptions;

public class ReportException extends RuntimeException{
    public ReportException(String message, Throwable cause) {
        super(message, cause);
    }
}
