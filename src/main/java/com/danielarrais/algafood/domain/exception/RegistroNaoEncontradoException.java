package com.danielarrais.algafood.domain.exception;

public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(String erro) {
        super(erro);
    }

    public RegistroNaoEncontradoException(String erro, Object... args) {
        super(String.format(erro, args));
    }

    public RegistroNaoEncontradoException(String nomeEntidade, Long idRegistro) {
        super(String.format("%s de código %d não existe", nomeEntidade, idRegistro));
    }
}

