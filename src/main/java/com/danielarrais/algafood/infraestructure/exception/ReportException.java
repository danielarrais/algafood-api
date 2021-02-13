package com.danielarrais.algafood.infraestructure.exception;

public class ReportException extends RuntimeException{
    public ReportException(String message, Throwable cause) {
        super(message, cause);
    }
}
