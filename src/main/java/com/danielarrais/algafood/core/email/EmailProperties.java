package com.danielarrais.algafood.core.email;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Data
@Component
@Validated
@ConfigurationProperties("algafood.email")
public class EmailProperties {

    @NotEmpty
    private String remetente;
    private ServicoEmail servicoEmail = ServicoEmail.FAKE;

    public enum ServicoEmail{
        FAKE, SMTP
    }
}
