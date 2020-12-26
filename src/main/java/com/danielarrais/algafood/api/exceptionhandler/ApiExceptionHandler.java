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

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<?> handleRegistroNaoEncontradoException(RegistroNaoEncontradoException e, WebRequest request) {
        Problem problem = Problem.builder()
                .status(HttpStatus.NOT_FOUND)
                .title("Registro não encontrado")
                .detail(e.getMessage())
                .build();

        return super.handleExceptionInternal(e, problem, new HttpHeaders(), problem.getHttpStatus(), request);
    }

    @ExceptionHandler(DependenciaNaoEncontradaException.class)
    public ResponseEntity<?> handleDependenciaNaoEncontradaException(DependenciaNaoEncontradaException e, WebRequest request) {
        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST)
                .title("Dependência não encontrada")
                .detail(e.getMessage())
                .build();

        return super.handleExceptionInternal(e, problem, new HttpHeaders(), problem.getHttpStatus(), request);
    }

    @ExceptionHandler(RegistroEmUsoException.class)
    public ResponseEntity<?> handleRegistroEmUsoException(RegistroEmUsoException e, WebRequest request) {
        Problem problem = Problem.builder()
                .status(HttpStatus.CONFLICT)
                .title("Registro em uso")
                .detail(e.getMessage())
                .build();

        return super.handleExceptionInternal(e, problem, new HttpHeaders(), problem.getHttpStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal( Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = Problem.builder()
                    .status(status)
                    .title(status.getReasonPhrase())
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .status(status)
                    .title((String) body)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
