package com.danielarrais.algafood.api.exceptionhandler.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {
    private HttpStatus status;
    private String type;
    private String title;
    private String detail;

    public String getStatus() {
        return String.format("%s %s", status.value(), status.getReasonPhrase());
    }

    @JsonIgnore
    public HttpStatus getHttpStatus() {
        return status;
    }
}
