package com.danielarrais.algafood.infraestructure.service.email;

import com.danielarrais.algafood.core.email.EmailProperties;
import com.danielarrais.algafood.domain.service.EnvioEmailService;
import com.danielarrais.algafood.infraestructure.exceptions.EmailExceptionException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EnvioEmailServiceImpl implements EnvioEmailService {
    private final JavaMailSender mailSender;
    private final EmailProperties emailProperties;

    public EnvioEmailServiceImpl(JavaMailSender mailSender, EmailProperties emailProperties) {
        this.mailSender = mailSender;
        this.emailProperties = emailProperties;
    }

    @Override
    public void enviar(Mensagem mensagem) {
        try {
            var mimeMessage = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(mimeMessage, "UTF-8");

            helper.setFrom(emailProperties.getRemetente());
            helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
            helper.setSubject(mensagem.getAssunto());
            helper.setText(mensagem.getCorpo(), true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailExceptionException("Não foi possível enviar o e-mail", e);
        }
    }
}
