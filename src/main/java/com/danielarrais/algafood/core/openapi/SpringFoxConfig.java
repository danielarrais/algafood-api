package com.danielarrais.algafood.core.openapi;

import com.danielarrais.algafood.api.exception.Problem;
import com.danielarrais.algafood.api.v1.dto.output.cidade.CidadeOutput;
import com.danielarrais.algafood.api.v1.dto.output.cidade.CidadeSimpleOutput;
import com.danielarrais.algafood.api.v1.dto.output.cozinha.CozinhaOutput;
import com.danielarrais.algafood.api.v1.dto.output.estado.EstadoOutput;
import com.danielarrais.algafood.api.v1.dto.output.formaPagamento.FormaPagamentoOutput;
import com.danielarrais.algafood.api.v1.dto.output.grupo.GrupoOutput;
import com.danielarrais.algafood.api.v1.dto.output.pedido.PedidoSimpleOutput;
import com.danielarrais.algafood.api.v1.dto.output.permissao.PermissaoOutput;
import com.danielarrais.algafood.api.v1.dto.output.restaurante.ProdutoOutput;
import com.danielarrais.algafood.api.v1.dto.output.restaurante.RestauranteFullOutput;
import com.danielarrais.algafood.api.v1.dto.output.restaurante.RestauranteSimpleOutput;
import com.danielarrais.algafood.api.v1.dto.output.usuario.UsuarioOutput;
import com.danielarrais.algafood.api.v1.dto.output.usuario.UsuarioSimpleOutput;
import com.danielarrais.algafood.api.v2.dto.output.cidade.CidadeOutputV2;
import com.danielarrais.algafood.api.v2.dto.output.cidade.CidadeSimpleOutputV2;
import com.danielarrais.algafood.core.openapi.model.CollectionModelOAS;
import com.danielarrais.algafood.core.openapi.model.LinksOAS;
import com.danielarrais.algafood.core.openapi.model.PageableOAS;
import com.danielarrais.algafood.core.openapi.model.PagedModelOAS;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.http.HttpStatus;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@EnableSwagger2
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

    @Bean
    public Docket apiDocketV1() {
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
        }}.toArray(new Tag[0]);

        var alternatePageTypeRules = new HashSet<AlternateTypeRule>(){{
            add(buildPageTypeRole(CidadeOutput.class));
            add(buildPageTypeRole(CozinhaOutput.class));
            add(buildPageTypeRole(EstadoOutput.class));
            add(buildPageTypeRole(GrupoOutput.class));
            add(buildPageTypeRole(PedidoSimpleOutput.class));
            add(buildPageTypeRole(PermissaoOutput.class));
            add(buildPageTypeRole(RestauranteFullOutput.class));
            add(buildPageTypeRole(UsuarioOutput.class));
            add(buildPageTypeRole(FormaPagamentoOutput.class));
        }}.toArray(new AlternateTypeRule[0]);

        var alternateListTypeRules = new HashSet<AlternateTypeRule>(){{
            add(buildListTypeRole(CidadeOutput.class));
            add(buildListTypeRole(CidadeSimpleOutput.class));
            add(buildListTypeRole(CozinhaOutput.class));
            add(buildListTypeRole(EstadoOutput.class));
            add(buildListTypeRole(FormaPagamentoOutput.class));
            add(buildListTypeRole(GrupoOutput.class));
            add(buildListTypeRole(PermissaoOutput.class));
            add(buildListTypeRole(UsuarioOutput.class));
            add(buildListTypeRole(ProdutoOutput.class));
            add(buildListTypeRole(RestauranteSimpleOutput.class));
            add(buildListTypeRole(UsuarioSimpleOutput.class));
        }}.toArray(new AlternateTypeRule[0]);

        return apiDocket("1")
                .alternateTypeRules(alternatePageTypeRules)
                .alternateTypeRules(alternateListTypeRules)
                .tags(tags[0], Arrays.copyOfRange(tags, 1, tags.length));
    }

    @Bean
    public Docket apiDocketV2() {
        var tags = new ArrayList<Tag>() {{
            add(new Tag("Cidades", "Gerencia as cidades"));
        }}.toArray(new Tag[0]);

        var alternatePageTypeRules = new HashSet<AlternateTypeRule>(){{
            add(buildPageTypeRole(CidadeOutputV2.class));
        }}.toArray(new AlternateTypeRule[0]);

        var alternateListTypeRules = new HashSet<AlternateTypeRule>(){{
            add(buildListTypeRole(CidadeOutput.class));
            add(buildListTypeRole(CidadeSimpleOutputV2.class));
        }}.toArray(new AlternateTypeRule[0]);

        return  apiDocket("2")
                .alternateTypeRules(alternatePageTypeRules)
                .alternateTypeRules(alternateListTypeRules)
                .tags(tags[0], Arrays.copyOfRange(tags, 1, tags.length));
    }

    private ApiInfo apiInfo(String version) {
        var contato = new Contact("Daniel Arrais", "danielarrais.dev", "contato@danielarrais.dev");

        return new ApiInfoBuilder()
                .title("Algafood API")
                .description("API aberta para clientes e restaurantes")
                .version(version)
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

    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }

    private ArrayList<ResponseMessage> globalGetResponses() {
        return new ArrayList<>() {{
            add(new ResponseMessageBuilder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .responseModel(new ModelRef("Problema"))
                    .message("Erro interno do servidor").build());
            add(new ResponseMessageBuilder()
                    .code(HttpStatus.NOT_ACCEPTABLE.value())
                    .responseModel(new ModelRef("Problema"))
                    .message("Recurso não possui representação que poderia ser aceita pelo consumidor").build());
        }};
    }

    private ArrayList<ResponseMessage> globalPostPutResponses() {
        return new ArrayList<>() {{
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
    }

    private ArrayList<ResponseMessage> globalDeleteResponses() {
        return new ArrayList<>() {{
            add(new ResponseMessageBuilder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .responseModel(new ModelRef("Problema"))
                    .message("Requisição inválida (erro do cliente)").build());
            add(new ResponseMessageBuilder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .responseModel(new ModelRef("Problema"))
                    .message("Erro interno no servidor").build());
        }};
    }

    private Class<?>[] ignoreParametersTypes() {
        return new HashSet<Class<?>>() {{
            add(URI.class);
            add(URL.class);
            add(URLStreamHandler.class);
            add(Resource.class);
            add(InputStream.class);
            add(File.class);
        }}.toArray(new Class[0]);
    }

    private AlternateTypeRule buildPageTypeRole(Class<?> classModel) {
        var typeResolver = new TypeResolver();

        return AlternateTypeRules.newRule(
                typeResolver.resolve(PagedModel.class, classModel),
                typeResolver.resolve(PagedModelOAS.class, classModel)
        );
    }

    private AlternateTypeRule buildListTypeRole(Class<?> classModel) {
        var typeResolver = new TypeResolver();

        return AlternateTypeRules.newRule(
                typeResolver.resolve(CollectionModel.class, classModel),
                typeResolver.resolve(CollectionModelOAS.class, classModel)
        );
    }

    private Docket apiDocket(String version) {
        var typeResolver = new TypeResolver();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(String.format("V%s", version))
                .select()
                .apis(basePackage("com.danielarrais.algafood.api"))
                .paths(PathSelectors.ant(String.format("/v%s/**", version))).build()
                .globalResponseMessage(RequestMethod.GET, globalGetResponses())
                .globalResponseMessage(RequestMethod.POST, globalPostPutResponses())
                .globalResponseMessage(RequestMethod.PUT, globalPostPutResponses())
                .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponses())
                .additionalModels(typeResolver.resolve(Problem.class))
                .directModelSubstitute(Pageable.class, PageableOAS.class)
                .directModelSubstitute(Links.class, LinksOAS[].class)
                .ignoredParameterTypes(ignoreParametersTypes())
                .apiInfo(apiInfo(version));
    }
}
