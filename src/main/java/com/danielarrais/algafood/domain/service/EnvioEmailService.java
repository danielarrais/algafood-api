package com.danielarrais.algafood.domain.service;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

public interface EnvioEmailService {
    void enviar(Mensagem mensagem);

    @Data
    @Builder
    class Mensagem {
        private Set<String> destinatarios;
        private String assunto;
        private String corpo;
    }
}
