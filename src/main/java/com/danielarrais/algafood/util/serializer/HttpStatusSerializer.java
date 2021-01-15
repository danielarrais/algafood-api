package com.danielarrais.algafood.util.serializer;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.springframework.http.HttpStatus;

public class HttpStatusSerializer extends StdConverter<HttpStatus, String> {
    @Override
    public String convert(HttpStatus httpStatus) {
        return String.format("%s %s", httpStatus.value(), httpStatus.getReasonPhrase());
    }
}
