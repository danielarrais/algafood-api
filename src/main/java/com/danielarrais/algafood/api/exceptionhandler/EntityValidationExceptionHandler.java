package com.danielarrais.algafood.api.exceptionhandler;

import com.danielarrais.algafood.api.exceptionhandler.util.ValidationProblem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static com.danielarrais.algafood.util.ValidationUtils.extractErrorsFrom;

@ControllerAdvice
public class EntityValidationExceptionHandler {
    private final ResponseEntityCustomExceptionHandler exceptionHandler;

    public EntityValidationExceptionHandler(ResponseEntityCustomExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {
        var errorsMap = extractErrorsFrom(e.getBindingResult());

        ValidationProblem validationProblem = ValidationProblem.builder()
                .status(HttpStatus.BAD_REQUEST)
                .title("Erros de validação")
                .errors(errorsMap)
                .detail("Um ou mais campos estão inválidos.")
                .build();

        return exceptionHandler.handleExceptionInternal(e, validationProblem, new HttpHeaders(), validationProblem.getHttpStatus(), request);
    }
}
