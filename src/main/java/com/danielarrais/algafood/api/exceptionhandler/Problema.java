package com.danielarrais.algafood.api.exceptionhandler;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Problema {
    private LocalDateTime dataHora;
    private String mensagem;
}
