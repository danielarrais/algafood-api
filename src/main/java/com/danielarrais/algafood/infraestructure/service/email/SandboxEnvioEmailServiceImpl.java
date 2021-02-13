package com.danielarrais.algafood.infraestructure.service.email;

import com.danielarrais.algafood.core.email.EmailProperties;
import freemarker.template.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class SandboxEnvioEmailServiceImpl extends EnvioEmailServiceImpl {
    private final EmailProperties emailProperties;

    public SandboxEnvioEmailServiceImpl(JavaMailSender mailSender, EmailProperties emailProperties, Configuration configurationFreemarker) {
        super(mailSender, emailProperties, configurationFreemarker);
        this.emailProperties = emailProperties;
    }

    @Override
    public void enviar(Mensagem mensagem) {
        mensagem.setDestinatarios(new HashSet<>(){{
            add(emailProperties.getSandbox().getDestinatario());
        }});
        super.enviar(mensagem);
    }
}
