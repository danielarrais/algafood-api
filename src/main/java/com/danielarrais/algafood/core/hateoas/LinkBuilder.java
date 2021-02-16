package com.danielarrais.algafood.core.hateoas;

import com.danielarrais.algafood.api.controller.cidade.CidadeController;
import com.danielarrais.algafood.api.controller.cozinha.CozinhaController;
import com.danielarrais.algafood.api.controller.estado.EstadoController;
import com.danielarrais.algafood.api.controller.formaPagamento.FormaPagamentoController;
import com.danielarrais.algafood.api.controller.pedido.PedidoController;
import com.danielarrais.algafood.api.controller.restaurante.RestauranteController;
import com.danielarrais.algafood.api.controller.restaurante.RestauranteProdutoController;
import com.danielarrais.algafood.api.controller.usuario.UsuarioController;
import com.danielarrais.algafood.domain.filter.PedidoFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;

import static org.springframework.hateoas.TemplateVariable.VariableType.REQUEST_PARAM;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class LinkBuilder {
    private static final TemplateVariables VARIABLES_PAGEABLE = new TemplateVariables(
            new TemplateVariable("page", REQUEST_PARAM),
            new TemplateVariable("size", REQUEST_PARAM),
            new TemplateVariable("sort", REQUEST_PARAM)
    );


    public static Link linkBuscarPedido(String codigo) {
        return linkTo(methodOn(PedidoController.class).buscar(codigo)).withSelfRel();
    }

    public static Link linkBuscarRestaurante(Long id) {
        return linkTo(methodOn(RestauranteController.class).buscar(id)).withSelfRel();
    }

    public static Link linkBuscarFormaPagamento(Long id) {
        return linkTo(methodOn(FormaPagamentoController.class).buscar(id)).withSelfRel();
    }

    public static Link linkBuscarUsuario(Long id) {
        return linkTo(methodOn(UsuarioController.class).buscar(id)).withSelfRel();
    }

    public static Link linkUsuarios() {
        return linkTo(methodOn(UsuarioController.class)
                .listar(Pageable.unpaged())).withRel("usuarios");
    }

    public static Link linkBuscarCidade(Long id) {
        return linkTo(methodOn(CidadeController.class).buscar(id)).withSelfRel();
    }

    public static Link linkBuscarProduto(Long idRestaurante, Long idProduto) {
        return linkTo(methodOn(RestauranteProdutoController.class)
                .buscar(idRestaurante, idProduto))
                .withRel("produto");
    }

    public static Link linkCidades() {
        return linkTo(methodOn(CidadeController.class)
                .listar(Pageable.unpaged())).withRel("cidades");
    }

    public static Link linkEstados() {
        return linkTo(methodOn(EstadoController.class)
                .listar(Pageable.unpaged())).withRel("estados");
    }

    public static Link linkCozinhas() {
        return linkTo(methodOn(CozinhaController.class)
                .listar(Pageable.unpaged())).withRel("cozinhas");
    }

    public static Link linkBuscarEstado(Long id) {
        return linkTo(methodOn(EstadoController.class).buscar(id)).withSelfRel();
    }

    public static Link linkBuscarCozinha(Long id) {
        return linkTo(methodOn(CozinhaController.class).buscar(id)).withSelfRel();
    }

    public static Link linkPedidos() {
        var urlPedidos = linkTo(methodOn(PedidoController.class).listar(null, null)).toUri().toString();

        var variablesFiltroPedido = new TemplateVariables(
                new TemplateVariable(PedidoFilter.Fields.clienteId, REQUEST_PARAM),
                new TemplateVariable(PedidoFilter.Fields.dataCriacaoFim, REQUEST_PARAM),
                new TemplateVariable(PedidoFilter.Fields.dataCriacaoInicio, REQUEST_PARAM),
                new TemplateVariable(PedidoFilter.Fields.restauranteId, REQUEST_PARAM)
        );

        return Link.of(UriTemplate.of(urlPedidos, VARIABLES_PAGEABLE.concat(variablesFiltroPedido)), "pedidos");
    }
}
