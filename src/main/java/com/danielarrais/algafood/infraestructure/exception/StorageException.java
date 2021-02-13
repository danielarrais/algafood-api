package com.danielarrais.algafood.infraestructure.exception;

public class StorageException extends RuntimeException {
    public StorageException(String message, Exception cause) {
        super(message, cause);
    }
}

