package com.danielarrais.algafood.infraestructure.service.email;

import com.danielarrais.algafood.core.email.EmailProperties;
import com.danielarrais.algafood.domain.service.EnvioEmailService;
import com.danielarrais.algafood.infraestructure.exceptions.EmailExceptionException;
import freemarker.template.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Service
public class EnvioEmailServiceImpl implements EnvioEmailService {
    private final JavaMailSender mailSender;
    private final EmailProperties emailProperties;
    private final Configuration configurationFreemarker;

    public EnvioEmailServiceImpl(JavaMailSender mailSender, EmailProperties emailProperties, Configuration configurationFreemarker) {
        this.mailSender = mailSender;
        this.emailProperties = emailProperties;
        this.configurationFreemarker = configurationFreemarker;
    }

    @Override
    public void enviar(Mensagem mensagem) {
        try {
            var mimeMessage = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            var corpo = processarTemplate(mensagem);

            helper.setFrom(emailProperties.getRemetente());
            helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
            helper.setSubject(mensagem.getAssunto());
            helper.setText(corpo, true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailExceptionException("Não foi possível enviar o e-mail", e);
        }
    }

    private String processarTemplate(Mensagem mensagem) {
        try {
            var template = configurationFreemarker.getTemplate(mensagem.getTemplate().getNome());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getOutrosDados());
        } catch (Exception e) {
            throw new EmailExceptionException("Não foi possível montar o template do emaiil", e);
        }
    }
}
