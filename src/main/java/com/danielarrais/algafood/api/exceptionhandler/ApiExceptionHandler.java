package com.danielarrais.algafood.api.exceptionhandler;

import com.danielarrais.algafood.domain.exception.DependenciaNaoEncontradaException;
import com.danielarrais.algafood.domain.exception.RegistroEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<?> tratarRegistroNaoEncontradoException(RegistroNaoEncontradoException e) {
        Problema problema = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
    }

    @ExceptionHandler(DependenciaNaoEncontradaException.class)
    public ResponseEntity<?> tratarDependenciaNaoEncontradaException(DependenciaNaoEncontradaException e) {
        Problema problema = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
    }

    @ExceptionHandler(RegistroEmUsoException.class)
    public ResponseEntity<?> tratarRegistroEmUsoException(RegistroEmUsoException e) {
        Problema problema = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(problema);
    }
}
