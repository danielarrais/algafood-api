package com.danielarrais.algafood.domain.exception;

public class RegistroNaoEncontradoException extends RuntimeException {
    public RegistroNaoEncontradoException(Long idRegistro) {
        super(String.format("Registro de código %d não existe", idRegistro));
    }

    public RegistroNaoEncontradoException(String erro) {
        super(erro);
    }
}

