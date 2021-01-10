package com.danielarrais.algafood.api.exceptionhandler;

import com.danielarrais.algafood.api.exceptionhandler.util.Problem;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class DataSerializationExceptionHandler {
    private final ResponseEntityCustomExceptionHandler exceptionHandler;

    public DataSerializationExceptionHandler(ResponseEntityCustomExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e, WebRequest request) {

        Throwable cause = ExceptionUtils.getRootCause(e);

        if (cause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) cause, request);
        } else if (cause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) cause, request);
        }

        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST)
                .title("Mensagem incompreensível")
                .detail("O corpo da requisição está inválido. Verifique se a sintaxe da mensagem está correta.")
                .build();

        return exceptionHandler.handleExceptionInternal(e, problem, new HttpHeaders(), problem.getHttpStatus(), request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException e, WebRequest request) {
        String path = joinPath(e.getPath());

        String detail = String.format("A propriedade '%s' não existe. Corrija ou remova essa propriedade e tente novamente.", path);

        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST)
                .title("Mensagem incompreensível")
                .detail(detail)
                .build();

        return exceptionHandler.handleExceptionInternal(e, problem, new HttpHeaders(), problem.getHttpStatus(), request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException e, WebRequest request) {
        String path = joinPath(e.getPath());

        String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                path, e.getValue(), e.getTargetType().getSimpleName());

        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST)
                .title("Mensagem incompreensível")
                .detail(detail)
                .build();

        return exceptionHandler.handleExceptionInternal(e, problem, new HttpHeaders(), problem.getHttpStatus(), request);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));
    }
}
