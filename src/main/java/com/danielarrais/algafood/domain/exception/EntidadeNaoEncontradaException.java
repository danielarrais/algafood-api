package com.danielarrais.algafood.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(Long idRegistro) {
        super(String.format("Registro de código %d não existe", idRegistro));
    }

    public EntidadeNaoEncontradaException(String entidade, Long idRegistro) {
        super(String.format("%s de código %d não existe", entidade, idRegistro));
    }
}

