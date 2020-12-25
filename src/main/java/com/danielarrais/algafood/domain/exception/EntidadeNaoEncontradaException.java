package com.danielarrais.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus
public class EntidadeNaoEncontradaException extends ResponseStatusException {
    private Boolean dependencia = false;

    public EntidadeNaoEncontradaException(Long idRegistro) {
        super(HttpStatus.NOT_FOUND, String.format("Registro de código %d não existe", idRegistro));
    }

    public EntidadeNaoEncontradaException(String entidade, Long idRegistro) {
        this(entidade, idRegistro, false);
    }

    public EntidadeNaoEncontradaException(String entidade, Long idRegistro, Boolean dependencia) {
        super(HttpStatus.NOT_FOUND, String.format("%s de código %d não existe", entidade, idRegistro));
        this.dependencia = dependencia;
    }

    public Boolean isDependencia() {
        return dependencia;
    }
}

