package com.danielarrais.algafood.core.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Configuration
public class ValidationConfig {
    final ResourcePatternResolver resourceResolver;

    public ValidationConfig(ResourcePatternResolver resourceResolver) {
        this.resourceResolver = resourceResolver;
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean(MessageSource messageSource) {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource);

        return localValidatorFactoryBean;
    }

    @Bean
    public ResourceBundleMessageSource resourceBundleMessageSource() throws IOException {
        var messageSource = new ResourceBundleMessageSource();
        var resources = messagesResources();

        messageSource.setBasenames(resources);
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    private String[] messagesResources() throws IOException {
        return Arrays
                .stream(resourceResolver.getResources("classpath:messages/*.properties"))
                .map(resource -> {
                    var fileName = Objects.requireNonNull(resource.getFilename()).replace(".properties", "");
                    return String.format("messages/%s", fileName);
                }).toArray(String[]::new);
    }
}
