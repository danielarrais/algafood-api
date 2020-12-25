package com.danielarrais.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends RuntimeException {
    public EntidadeEmUsoException(Long idRegistro) {
        super(String.format("Registro de código %d não pode ser removido", idRegistro));
    }
}
