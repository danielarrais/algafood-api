package com.danielarrais.algafood.domain.exception;

public class RegistroEmUsoException extends RuntimeException {
    public RegistroEmUsoException(Long idRegistro) {
        super(String.format("Registro de código %d está em uso por outros registros e não pôde ser removido", idRegistro));
    }
}
