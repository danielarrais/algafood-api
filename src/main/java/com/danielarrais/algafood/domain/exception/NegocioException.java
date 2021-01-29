package com.danielarrais.algafood.domain.exception;

public class NegocioException extends RuntimeException {
    public NegocioException(String message) {
        super(message);
    }

    public NegocioException(String message, Exception e) {
        super(message, e);
    }

    public NegocioException(String erro, Object... args) {
        super(String.format(erro, args));
    }
}

