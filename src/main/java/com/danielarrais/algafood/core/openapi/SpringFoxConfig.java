package com.danielarrais.algafood.core.openapi;

import com.danielarrais.algafood.api.exception.Problem;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

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

        var typeProblemResolver = typeResolver.resolve(Problem.class);

        var globalDeleteResponses = new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .responseModel(new ModelRef("Problema"))
                    .message("Requisição inválida (erro do cliente)").build());
            add(new ResponseMessageBuilder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .responseModel(new ModelRef("Problema"))
                    .message("Erro interno no servidor").build());
        }};

        var globalGetResponses = new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .responseModel(new ModelRef("Problema"))
                    .message("Erro interno do servidor").build());
            add(new ResponseMessageBuilder()
                    .code(HttpStatus.NOT_ACCEPTABLE.value())
                    .responseModel(new ModelRef("Problema"))
                    .message("Recurso não possui representação que poderia ser aceita pelo consumidor").build());
        }};

        var globalPostPutResponses = new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .responseModel(new ModelRef("Problema"))
                    .message("Requisição inválida (erro do cliente)").build());
            add(new ResponseMessageBuilder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .responseModel(new ModelRef("Problema"))
                    .message("Erro interno no servidor").build());
            add(new ResponseMessageBuilder()
                    .code(HttpStatus.NOT_ACCEPTABLE.value())
                    .responseModel(new ModelRef("Problema"))
                    .message("Recurso não possui representação que poderia ser aceita pelo consumidor").build());
            add(new ResponseMessageBuilder()
                    .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                    .responseModel(new ModelRef("Problema"))
                    .message("Requisição recusada porque o corpo está em um formato não suportado").build());

        }};

        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(basePackage).build()
                .globalResponseMessage(RequestMethod.GET, globalGetResponses)
                .globalResponseMessage(RequestMethod.POST, globalPostPutResponses)
                .globalResponseMessage(RequestMethod.PUT, globalPostPutResponses)
                .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponses)
                .additionalModels(typeResolver.resolve(Problem.class))
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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
