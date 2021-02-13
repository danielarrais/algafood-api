package com.danielarrais.algafood.api.exception.handler;

import com.danielarrais.algafood.api.exception.Problem;
import com.danielarrais.algafood.api.exception.ValidationProblem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.danielarrais.algafood.util.ValidationUtils.extractErrorsFrom;

@ControllerAdvice
public class ResponseEntityCustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        String detail = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.";

        Problem problem = Problem.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .title("Erro no sistema")
                .detail(detail)
                .build();

        ex.printStackTrace();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), problem.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", e.getRequestURL());

        Problem problem = Problem.builder()
                .status(HttpStatus.NOT_FOUND)
                .title("Recurso não encontrado")
                .detail(detail)
                .build();

        return super.handleExceptionInternal(e, problem, headers, problem.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Problem problem = Problem.builder()
                .status(status)
                .title("Mensagem incompreensível")
                .detail("O corpo da requisição está inválido. Verifique se a sintaxe da mensagem está correta.")
                .build();

        return super.handleExceptionInternal(e, problem, headers, problem.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
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

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var errorsMap = extractErrorsFrom(ex.getBindingResult());

        ValidationProblem validationProblem = ValidationProblem.builder()
                .status(HttpStatus.BAD_REQUEST)
                .title("Erros de validação")
                .errors(errorsMap)
                .detail("Um ou mais campos estão inválidos.")
                .build();

        return handleExceptionInternal(ex, validationProblem, new HttpHeaders(), validationProblem.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return ResponseEntity.status(status).headers(headers).build();
    }
}
