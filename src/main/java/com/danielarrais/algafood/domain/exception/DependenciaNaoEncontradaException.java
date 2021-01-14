package com.danielarrais.algafood.domain.exception;

public class DependenciaNaoEncontradaException extends RegistroNaoEncontradoException {
    public DependenciaNaoEncontradaException(String entidade, Long idRegistro) {
        super(String.format("%s de código %d não existe", entidade, idRegistro));
    }
}

