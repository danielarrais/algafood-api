package com.danielarrais.algafood.core.openapi;

import com.danielarrais.algafood.api.exception.Problem;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.function.Consumer;

@EnableOpenApi
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {
        var typeResolver = new TypeResolver();
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

        var globalDeleteResponses = new ArrayList<Response>() {{
            add(new ResponseBuilder()
                    .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                    .representation(MediaType.APPLICATION_JSON).apply(builderModelProblema())
                    .description("Requisição inválida (erro do cliente)").build());
            add(new ResponseBuilder()
                    .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                    .representation(MediaType.APPLICATION_JSON).apply(builderModelProblema())
                    .description("Erro interno no servidor").build());
        }};

        var globalGetResponses = new ArrayList<Response>() {{
            add(new ResponseBuilder()
                    .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                    .representation(MediaType.APPLICATION_JSON).apply(builderModelProblema())
                    .description("Erro interno do servidor").build());
            add(new ResponseBuilder()
                    .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                    .representation(MediaType.APPLICATION_JSON).apply(builderModelProblema())
                    .description("Recurso não possui representação que poderia ser aceita pelo consumidor").build());
        }};

        var globalPostPutResponses = new ArrayList<Response>() {{
            add(new ResponseBuilder()
                    .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                    .representation(MediaType.APPLICATION_JSON).apply(builderModelProblema())
                    .description("Requisição inválida (erro do cliente)").build());
            add(new ResponseBuilder()
                    .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                    .representation(MediaType.APPLICATION_JSON).apply(builderModelProblema())
                    .description("Erro interno no servidor").build());
            add(new ResponseBuilder()
                    .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                    .representation(MediaType.APPLICATION_JSON).apply(builderModelProblema())
                    .description("Recurso não possui representação que poderia ser aceita pelo consumidor").build());
            add(new ResponseBuilder()
                    .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                    .representation(MediaType.APPLICATION_JSON).apply(builderModelProblema())
                    .description("Requisição recusada porque o corpo está em um formato não suportado").build());

        }};

        return new Docket(DocumentationType.OAS_30)
                .select().apis(basePackage).build()
                .globalResponses(HttpMethod.GET, globalGetResponses)
                .globalResponses(HttpMethod.POST, globalPostPutResponses)
                .globalResponses(HttpMethod.PUT, globalPostPutResponses)
                .globalResponses(HttpMethod.DELETE, globalDeleteResponses)
                .additionalModels(typeResolver.resolve(Problem.class))
                .apiInfo(apiInfo())
                .tags(tags.remove(0), tags.toArray(new Tag[0]));
    }

    private Consumer<RepresentationBuilder> builderModelProblema() {
        return r -> r.model(m -> m.name("Problema")
                .referenceModel(
                        ref -> ref.key(
                                k -> k.qualifiedModelName(
                                        q -> q.name("Problema").namespace("com.danielarrais.algafood.api.exception")
                                ))));
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
