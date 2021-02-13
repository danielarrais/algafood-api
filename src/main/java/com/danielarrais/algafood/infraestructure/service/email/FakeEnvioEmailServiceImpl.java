package com.danielarrais.algafood.infraestructure.service.email;

import com.danielarrais.algafood.core.email.EmailProperties;
import com.danielarrais.algafood.domain.service.EnvioEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Slf4j
@Component
public class FakeEnvioEmailServiceImpl implements EnvioEmailService {
    private final EmailProperties emailProperties;

    public FakeEnvioEmailServiceImpl(EmailProperties emailProperties) {
        this.emailProperties = emailProperties;
    }

    @Override
    public void enviar(Mensagem mensagem) {
        var email = "Email enviado de {0} para {1} usando o template {2}!";

        mensagem.getDestinatarios().forEach(destinatario -> {
            log.info(MessageFormat.format(email, emailProperties.getRemetente(), destinatario, mensagem.getTemplate().getNome()));
        });
    }
}
