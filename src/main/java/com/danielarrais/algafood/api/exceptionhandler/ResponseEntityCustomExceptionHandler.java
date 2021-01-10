package com.danielarrais.algafood.api.exceptionhandler;

import com.danielarrais.algafood.api.exceptionhandler.util.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseEntityCustomExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Problem problem = Problem.builder()
                .status(status)
                .title("Mensagem incompreensível")
                .detail("O corpo da requisição está inválido. Verifique se a sintaxe da mensagem está correta.")
                .build();

        return super.handleExceptionInternal(e, problem, headers, problem.getHttpStatus(), request);
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
