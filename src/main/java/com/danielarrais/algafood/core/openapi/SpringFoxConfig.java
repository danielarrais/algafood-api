package com.danielarrais.algafood.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {
        var basePackage = RequestHandlerSelectors.basePackage("com.danielarrais.algafood.api");

        return new Docket(DocumentationType.OAS_30)
                .select().apis(basePackage)
                .build();
    }
}
