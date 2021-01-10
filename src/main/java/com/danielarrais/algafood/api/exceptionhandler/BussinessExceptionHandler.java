package com.danielarrais.algafood.api.exceptionhandler;

import com.danielarrais.algafood.api.exceptionhandler.util.Problem;
import com.danielarrais.algafood.domain.exception.DependenciaNaoEncontradaException;
import com.danielarrais.algafood.domain.exception.RegistroEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class BussinessExceptionHandler {
    private final ResponseEntityCustomExceptionHandler exceptionHandler;

    public BussinessExceptionHandler(ResponseEntityCustomExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<?> handleRegistroNaoEncontradoException(RegistroNaoEncontradoException e, WebRequest request) {
        Problem problem = Problem.builder()
                .status(HttpStatus.NOT_FOUND)
                .title("Registro não encontrado")
                .detail(e.getMessage())
                .build();

        return exceptionHandler.handleExceptionInternal(e, problem, new HttpHeaders(), problem.getHttpStatus(), request);
    }

    @ExceptionHandler(DependenciaNaoEncontradaException.class)
    public ResponseEntity<?> handleDependenciaNaoEncontradaException(DependenciaNaoEncontradaException e, WebRequest request) {
        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST)
                .title("Dependência não encontrada")
                .detail(e.getMessage())
                .build();

        return exceptionHandler.handleExceptionInternal(e, problem, new HttpHeaders(), problem.getHttpStatus(), request);
    }

    @ExceptionHandler(RegistroEmUsoException.class)
    public ResponseEntity<?> handleRegistroEmUsoException(RegistroEmUsoException e, WebRequest request) {
        Problem problem = Problem.builder()
                .status(HttpStatus.CONFLICT)
                .title("Registro em uso")
                .detail(e.getMessage())
                .build();

        return exceptionHandler.handleExceptionInternal(e, problem, new HttpHeaders(), problem.getHttpStatus(), request);
    }
}
