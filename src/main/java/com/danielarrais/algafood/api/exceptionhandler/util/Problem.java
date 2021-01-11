package com.danielarrais.algafood.api.exceptionhandler.util;

import com.danielarrais.algafood.util.serializer.HttpStatusSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {
    @JsonSerialize(converter = HttpStatusSerializer.class)
    private HttpStatus status;
    private String type;
    private String title;
    private String detail;

    @JsonIgnore
    public HttpStatus getHttpStatus() {
        return status;
    }
}
