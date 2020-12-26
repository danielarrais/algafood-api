package com.danielarrais.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DependenciaNaoEncontradaException extends RegistroNaoEncontradaException {
    public DependenciaNaoEncontradaException(String entidade, Long idRegistro) {
        super(String.format("%s de código %d não existe", entidade, idRegistro));
    }
}

