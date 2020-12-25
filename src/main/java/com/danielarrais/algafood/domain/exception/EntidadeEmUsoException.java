package com.danielarrais.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntidadeEmUsoException extends ResponseStatusException {
    public EntidadeEmUsoException(Long idRegistro) {
        super(HttpStatus.CONFLICT, String.format("Registro de código %d não pode ser removido", idRegistro));
    }
}
