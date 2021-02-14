package com.danielarrais.algafood.api.exception;

import com.danielarrais.algafood.util.serializer.HttpStatusSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Problem")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {
    @JsonSerialize(converter = HttpStatusSerializer.class)
    private HttpStatus status;
    private String type;
    private String title;
    private String detail;
    private List<ObjectError> errors;
}
