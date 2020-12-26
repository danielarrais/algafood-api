package com.danielarrais.algafood.api.exceptionhandler;

import com.danielarrais.algafood.domain.exception.DependenciaNaoEncontradaException;
import com.danielarrais.algafood.domain.exception.RegistroEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

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

    @Override
    protected ResponseEntity<Object> handleExceptionInternal( Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = Problema.builder()
                    .dataHora(LocalDateTime.now())
                    .mensagem(status.getReasonPhrase())
                    .build();
        } else if (body instanceof String) {
            body = Problema.builder()
                    .dataHora(LocalDateTime.now())
                    .mensagem((String) body)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
