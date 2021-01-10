package com.danielarrais.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegistroNaoEncontradoException extends RuntimeException {
    public RegistroNaoEncontradoException(Long idRegistro) {
        super(String.format("Registro de código %d não existe", idRegistro));
    }

    public RegistroNaoEncontradoException(String erro) {
        super(erro);
    }
}

