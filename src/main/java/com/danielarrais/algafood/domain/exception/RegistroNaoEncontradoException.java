package com.danielarrais.algafood.domain.exception;

public class RegistroNaoEncontradoException extends RuntimeException {
    public RegistroNaoEncontradoException(String nomeEntidade, Long idRegistro) {
        super(String.format("%s de código %d não existe", nomeEntidade, idRegistro));
    }

    public RegistroNaoEncontradoException(String erro) {
        super(erro);
    }
}

