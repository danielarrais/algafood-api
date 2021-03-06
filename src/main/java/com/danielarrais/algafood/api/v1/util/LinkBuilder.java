package com.danielarrais.algafood.api.v1.util;

import com.danielarrais.algafood.api.v1.controller.cidade.CidadeController;
import com.danielarrais.algafood.api.v1.controller.cozinha.CozinhaController;
import com.danielarrais.algafood.api.v1.controller.estado.EstadoController;
import com.danielarrais.algafood.api.v1.controller.estatistica.EstatisticaController;
import com.danielarrais.algafood.api.v1.controller.formaPagamento.FormaPagamentoController;
import com.danielarrais.algafood.api.v1.controller.grupo.GrupoController;
import com.danielarrais.algafood.api.v1.controller.grupo.GrupoPermissoesController;
import com.danielarrais.algafood.api.v1.controller.pedido.FluxoPedidoController;
import com.danielarrais.algafood.api.v1.controller.pedido.PedidoController;
import com.danielarrais.algafood.api.v1.controller.permissao.PermissaoController;
import com.danielarrais.algafood.api.v1.controller.restaurante.*;
import com.danielarrais.algafood.api.v1.controller.usuario.UsuarioController;
import com.danielarrais.algafood.api.v1.controller.usuario.UsuarioGrupoController;
import com.danielarrais.algafood.domain.filter.PedidoFilter;
import com.danielarrais.algafood.domain.filter.VendaDiariaFilter;
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

    public static Link linkEstatisticas() {
        return linkTo(methodOn(EstatisticaController.class).root()).withRel("estatisticas");
    }

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

    public static Link linkBuscarPermissao(Long id) {
        return linkTo(methodOn(PermissaoController.class).buscar(id)).withSelfRel();
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

    public static Link linkAbrirRestaurante(Long idRestaurante) {
        return linkTo(methodOn(RestauranteController.class).abrir(idRestaurante)).withRel("abrir");
    }

    public static Link linkFecharRestaurante(Long idRestaurante) {
        return linkTo(methodOn(RestauranteController.class).fechar(idRestaurante)).withRel("fechar");
    }

    public static Link linkAssociarPermissaoGrupo(Long idGrupo) {
        return linkTo(methodOn(GrupoPermissoesController.class).associar(idGrupo, null)).withRel("associar");
    }

    public static Link linkDesassociarPermissaoGrupo(Long idGrupo, Long idPermissao) {
        return linkTo(methodOn(GrupoPermissoesController.class).associar(idGrupo, idPermissao)).withRel("desassociar");
    }

    public static Link linkAssociarGrupoUsuario(Long idUsuario) {
        return linkTo(methodOn(UsuarioGrupoController.class).associar(idUsuario, null)).withRel("associar");
    }

    public static Link linkDesassociarGrupoUsuario(Long idUsuario, Long idGrupo) {
        return linkTo(methodOn(UsuarioGrupoController.class).associar(idUsuario, idGrupo)).withRel("desassociar");
    }

    public static Link linkAssociarFormaPagamentoRestaurante(Long idRestaurante) {
        return linkTo(methodOn(RestauranteFormaPagamentoController.class).associar(idRestaurante, null)).withRel("associar");
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

    public static Link linkVendasDiarias() {
        var urlPedidos = linkTo(methodOn(EstatisticaController.class).findVendasDiariasInJSON(null)).toUri().toString();

        var variablesFiltroPedido = new TemplateVariables(
                new TemplateVariable(VendaDiariaFilter.Fields.restauranteId, REQUEST_PARAM),
                new TemplateVariable(VendaDiariaFilter.Fields.dataCriacaoInicio, REQUEST_PARAM),
                new TemplateVariable(VendaDiariaFilter.Fields.dataCriacaoFim, REQUEST_PARAM)
        );

        return Link.of(UriTemplate.of(urlPedidos, VARIABLES_PAGEABLE.concat(variablesFiltroPedido)), "vendas-diarias");
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

    public static Link linkGrupos(Long idUsuario) {
        return linkTo(methodOn(UsuarioGrupoController.class).listar(idUsuario)).withRel("grupos");
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

    public static Link linkPermissoes() {
        var url = linkTo(methodOn(PermissaoController.class).listar(null)).toUri().toString();
        return Link.of(UriTemplate.of(url, VARIABLES_PAGEABLE), "permissoes");
    }

    public static Link linkUsuarios() {
        return linkUsuarios("usuarios");
    }
}
