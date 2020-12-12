package com.danielarrais.algafood.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(Long idRegistro) {
        super(String.format("Registro de código %d não pode ser encontrado", idRegistro));
    }
}

