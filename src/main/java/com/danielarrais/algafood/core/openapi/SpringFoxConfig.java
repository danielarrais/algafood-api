package com.danielarrais.algafood.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@EnableOpenApi
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {
        var basePackage = RequestHandlerSelectors.basePackage("com.danielarrais.algafood.api");

        var tags = new ArrayList<Tag>() {{
            add(new Tag("Cidades", "Gerencia as cidades"));
            add(new Tag("Cozinhas", "Gerencia as cozinhas"));
            add(new Tag("Estados", "Gerencia os estados"));
            add(new Tag("Estatisticas", "Prover APIs para consulta de dados"));
            add(new Tag("Formas de Pagamento", "Gerencia as formas de pagamento"));
            add(new Tag("Grupos de Permissões", "Gerencia os grupos de permissões de acesso"));
            add(new Tag("Pedidos", "Gerencia os pedidos"));
            add(new Tag("Alteração de Status de Pedido", "Gerencia os status dos pedidos"));
            add(new Tag("Permissões", "Gerencia as permissões de acesso"));
            add(new Tag("Restaurantes", "Gerencia os restaurantes"));
            add(new Tag("Formas de Pagamento do Restaurante", "Gerencia as formas de pagamento de um restaurante"));
            add(new Tag("Produtos", "Gerencia os produtos de um restaurante"));
            add(new Tag("Responsáveis do Restaurante", "Gerencia os responsáveis de um restaurante"));
            add(new Tag("Usuários", "Gerencia os usuários do sistema"));
            add(new Tag("Grupos de Permissões do Usuário", "Gerencia os usuários do sistema"));
        }};

        return new Docket(DocumentationType.OAS_30)
                .select().apis(basePackage)
                .build()
                .apiInfo(apiInfo())
                .tags(tags.remove(0), tags.toArray(new Tag[0]));
    }

    public ApiInfo apiInfo() {
        var contato = new Contact("Daniel Arrais", "danielarrais.dev", "contato@danielarrais.dev");

        return new ApiInfoBuilder()
                .title("Algafood API")
                .description("API aberta para clientes e restaurantes")
                .version("1")
                .contact(contato)
                .build();
    }
}
