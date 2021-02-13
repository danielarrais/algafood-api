package com.danielarrais.algafood.infraestructure.exceptions;

public class StorageException extends RuntimeException {
    public StorageException(String message, Exception cause) {
        super(message, cause);
    }
}

