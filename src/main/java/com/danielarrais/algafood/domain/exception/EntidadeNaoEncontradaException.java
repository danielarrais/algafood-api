package com.danielarrais.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends RuntimeException {
    private Boolean dependencia = false;

    public EntidadeNaoEncontradaException(Long idRegistro) {
        super(String.format("Registro de código %d não existe", idRegistro));
    }

    public EntidadeNaoEncontradaException(String entidade, Long idRegistro) {
        this(entidade, idRegistro, false);
    }

    public EntidadeNaoEncontradaException(String entidade, Long idRegistro, Boolean dependencia) {
        super(String.format("%s de código %d não existe", entidade, idRegistro));
        this.dependencia = dependencia;
    }

    public Boolean isDependencia() {
        return dependencia;
    }
}

