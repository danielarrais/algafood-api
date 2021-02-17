package com.danielarrais.algafood.core.hateoas;

import com.danielarrais.algafood.api.controller.cidade.CidadeController;
import com.danielarrais.algafood.api.controller.cozinha.CozinhaController;
import com.danielarrais.algafood.api.controller.estado.EstadoController;
import com.danielarrais.algafood.api.controller.formaPagamento.FormaPagamentoController;
import com.danielarrais.algafood.api.controller.grupo.GrupoController;
import com.danielarrais.algafood.api.controller.grupo.GrupoPermissoesController;
import com.danielarrais.algafood.api.controller.pedido.FluxoPedidoController;
import com.danielarrais.algafood.api.controller.pedido.PedidoController;
import com.danielarrais.algafood.api.controller.restaurante.*;
import com.danielarrais.algafood.api.controller.usuario.UsuarioController;
import com.danielarrais.algafood.domain.filter.PedidoFilter;
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

    public static Link linkBuscarCidade(Long id) {
        return linkTo(methodOn(CidadeController.class).buscar(id)).withSelfRel();
    }

    public static Link linkBuscarProduto(Long idRestaurante, Long idProduto) {
        return linkTo(methodOn(RestauranteProdutoController.class)
                .buscar(idRestaurante, idProduto))
                .withRel("produto");
    }

    public static Link linkFotoProduto(Long idRestaurante, Long idProduto) {
        return linkTo(methodOn(RestauranteFotoProdutoController.class)
                .buscar(idRestaurante, idProduto))
                .withRel("foto");
    }

    public static Link linkBuscarEstado(Long id) {
        return linkTo(methodOn(EstadoController.class).buscar(id)).withSelfRel();
    }

    public static Link linkBuscarGrupo(Long id) {
        return linkTo(methodOn(GrupoController.class).buscar(id)).withSelfRel();
    }

    public static Link linkBuscarCozinha(Long id) {
        return linkTo(methodOn(CozinhaController.class).buscar(id)).withSelfRel();
    }

    public static Link linkConfimacaoPedido(String codigo) {
        return linkTo(methodOn(FluxoPedidoController.class).confirmar(codigo)).withRel("confirmar");
    }

    public static Link linkEntregaPedido(String codigo) {
        return linkTo(methodOn(FluxoPedidoController.class).entregar(codigo)).withRel("entregar");
    }

    public static Link linkCancelamentoPedido(String codigo) {
        return linkTo(methodOn(FluxoPedidoController.class).cancelar(codigo)).withRel("cancelar");
    }

    public static Link linkResponsaveisRestaurante(Long idRestaurante) {
        return linkTo(methodOn(RestauranteUsuarioResponsavelController.class).listar(idRestaurante)).withRel("responsaveis");
    }

    public static Link linkFormasPagamentoRestaurante(Long idRestaurante) {
        return linkTo(methodOn(RestauranteFormaPagamentoController.class).listar(idRestaurante)).withRel("formas-pagamento");
    }

    public static Link linkAtivarRestaurante(Long idRestaurante) {
        return linkTo(methodOn(RestauranteController.class).ativar(idRestaurante)).withRel("ativar");
    }

    public static Link linkInativarRestaurante(Long idRestaurante) {
        return linkTo(methodOn(RestauranteController.class).inativar(idRestaurante)).withRel("inativar");
    }

    public static Link linkAssociarFormaPagamentoRestaurante(Long idRestaurante) {
        return linkTo(methodOn(RestauranteFormaPagamentoController.class).associar(idRestaurante, null)).withRel("associar");
    }

    public static Link linkAbrirRestaurante(Long idRestaurante) {
        return linkTo(methodOn(RestauranteController.class).abrir(idRestaurante)).withRel("abrir");
    }

    public static Link linkFecharRestaurante(Long idRestaurante) {
        return linkTo(methodOn(RestauranteController.class).fechar(idRestaurante)).withRel("fechar");
    }

    public static Link linkDesassociarFormaPagamentoRestaurante(Long idRestaurante, Long idFormaPagamento) {
        return linkTo(methodOn(RestauranteFormaPagamentoController.class).desassociar(idRestaurante, idFormaPagamento)).withRel("desassociar");
    }

    public static Link linkAssociarResponsavelRestaurante(Long idRestaurante) {
        return linkTo(methodOn(RestauranteUsuarioResponsavelController.class).associar(idRestaurante, null)).withRel("associar");
    }

    public static Link linkDesassociarResponsavelRestaurante(Long idRestaurante, Long idResponsavel) {
        return linkTo(methodOn(RestauranteUsuarioResponsavelController.class).desassociar(idRestaurante, idResponsavel)).withRel("desassociar");
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

    public static Link linkPermissoes(Long idGrupo) {
        return linkTo(methodOn(GrupoPermissoesController.class).listar(idGrupo)).withRel("permissoes");
    }

    public static Link linkProdutos(Long idRestaurante) {
        return linkTo(methodOn(RestauranteProdutoController.class).listar(idRestaurante)).withRel("produtos");
    }

    public static Link linkRestaurantes() {
        var url = linkTo(methodOn(RestauranteController.class).listar(null)).toUri().toString();
        return Link.of(UriTemplate.of(url, VARIABLES_PAGEABLE), "restaurantes");
    }

    public static Link linkCidades() {
        var url = linkTo(methodOn(CidadeController.class).listar(null)).toUri().toString();
        return Link.of(UriTemplate.of(url, VARIABLES_PAGEABLE), "cidades");
    }

    public static Link linkEstados() {
        var url = linkTo(methodOn(EstadoController.class).listar(null)).toUri().toString();
        return Link.of(UriTemplate.of(url, VARIABLES_PAGEABLE), "estados");
    }

    public static Link linkCozinhas() {
        var url = linkTo(methodOn(CozinhaController.class).listar(null)).toUri().toString();
        return Link.of(UriTemplate.of(url, VARIABLES_PAGEABLE), "cozinhas");
    }

    public static Link linkFormasPagamento() {
        var url = linkTo(methodOn(FormaPagamentoController.class).listar(null)).toUri().toString();
        return Link.of(UriTemplate.of(url, VARIABLES_PAGEABLE), "formas-pagamento");
    }

    public static Link linkGrupos() {
        var url = linkTo(methodOn(GrupoController.class).listar(null)).toUri().toString();
        return Link.of(UriTemplate.of(url, VARIABLES_PAGEABLE), "grupos");
    }

    public static Link linkUsuarios(String rel) {
        var url = linkTo(methodOn(UsuarioController.class).listar(null)).toUri().toString();
        return Link.of(UriTemplate.of(url, VARIABLES_PAGEABLE), rel);
    }

    public static Link linkUsuarios() {
        return linkUsuarios("usuarios");
    }
}
