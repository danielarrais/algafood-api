package com.danielarrais.algafood.core.email;

import com.danielarrais.algafood.domain.service.EnvioEmailService;
import com.danielarrais.algafood.infraestructure.service.email.EnvioEmailServiceImpl;
import com.danielarrais.algafood.infraestructure.service.email.FakeEnvioEmailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class EmailConfig {
    private final EmailProperties emailProperties;
    private final JavaMailSender mailSender;
    private final freemarker.template.Configuration configurationFreemarker;

    public EmailConfig(EmailProperties emailProperties, JavaMailSender mailSender, freemarker.template.Configuration configurationFreemarker) {
        this.emailProperties = emailProperties;
        this.mailSender = mailSender;
        this.configurationFreemarker = configurationFreemarker;
    }

    @Bean
    public EnvioEmailService envioEmailService() {
        if (emailProperties.getServicoEmail().equals(EmailProperties.ServicoEmail.FAKE)) {
            return new FakeEnvioEmailServiceImpl(emailProperties);
        } else {
            return new EnvioEmailServiceImpl(mailSender, emailProperties, configurationFreemarker);
        }
    }
}
