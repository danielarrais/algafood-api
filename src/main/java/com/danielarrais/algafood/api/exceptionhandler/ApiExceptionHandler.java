package com.danielarrais.algafood.api.exceptionhandler;

import com.danielarrais.algafood.domain.exception.DependenciaNaoEncontradaException;
import com.danielarrais.algafood.domain.exception.RegistroEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

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
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Problem problem = Problem.builder()
                .status(status)
                .title("Mensagem incompreensível")
                .detail("O corpo da requisição está inválido. Verifique se a sintaxe da mensagem está correta.")
                .build();

        return super.handleExceptionInternal(e, problem, headers, problem.getHttpStatus(), request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e, WebRequest request) {

        Throwable cause = ExceptionUtils.getRootCause(e);

        if (cause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) cause, request);
        } if (cause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) cause, request);
        }

        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST)
                .title("Mensagem incompreensível")
                .detail("O corpo da requisição está inválido. Verifique se a sintaxe da mensagem está correta.")
                .build();

        return super.handleExceptionInternal(e, problem, new HttpHeaders(), problem.getHttpStatus(), request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException e, WebRequest request) {
        String path = joinPath(e.getPath());

        String detail = String.format("A propriedade '%s' não existe. Corrija ou remova essa propriedade e tente novamente.", path);

        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST)
                .title("Mensagem incompreensível")
                .detail(detail)
                .build();

        return super.handleExceptionInternal(e, problem, new HttpHeaders(), problem.getHttpStatus(), request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException e, WebRequest request) {
        String path = e.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));

        String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                path, e.getValue(), e.getTargetType().getSimpleName());

        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST)
                .title("Mensagem incompreensível")
                .detail(detail)
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

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));
    }
}
