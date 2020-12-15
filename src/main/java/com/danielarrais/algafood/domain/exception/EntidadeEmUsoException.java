package com.danielarrais.algafood.domain.exception;

public class EntidadeEmUsoException extends RuntimeException {
    public EntidadeEmUsoException(Long idRegistro) {
        super(String.format("Registro de código %d não pode ser removido", idRegistro));
    }
}
