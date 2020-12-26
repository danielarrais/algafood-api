package com.danielarrais.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegistroNaoEncontradaException extends RuntimeException {
    public RegistroNaoEncontradaException(Long idRegistro) {
        super(String.format("Registro de código %d não existe", idRegistro));
    }
    public RegistroNaoEncontradaException(String erro) {
        super(erro);
    }
}

