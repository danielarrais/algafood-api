package com.danielarrais.algafood.domain.service;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Singular;

import java.util.Map;
import java.util.Set;

public interface EnvioEmailService {
    void enviar(Mensagem mensagem);

    @Data
    @Builder
    class Mensagem {
        @Singular
        private Set<String> destinatarios;
        private String assunto;
        private TemplateEmail template;

        @Singular("outroDado")
        private Map<String, Object> outrosDados;
    }

    @Getter
    enum TemplateEmail {
        PEDIDO_CONFIRMADO("pedido-confirmado.ftl"),
        PEDIDO_CANCELADO("pedido-cancelado.ftl");

        private String nome;

        TemplateEmail(String nome) {
            this.nome = nome;
        }
    }
}
