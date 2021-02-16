package com.danielarrais.algafood.api.assembler.pedido;

import com.danielarrais.algafood.api.controller.cidade.CidadeController;
import com.danielarrais.algafood.api.controller.formaPagamento.FormaPagamentoController;
import com.danielarrais.algafood.api.controller.pedido.PedidoController;
import com.danielarrais.algafood.api.controller.restaurante.RestauranteController;
import com.danielarrais.algafood.api.controller.restaurante.RestauranteProdutoController;
import com.danielarrais.algafood.api.controller.usuario.UsuarioController;
import com.danielarrais.algafood.api.dto.output.pedido.PedidoFullOutput;
import com.danielarrais.algafood.domain.filter.PedidoFilter;
import com.danielarrais.algafood.domain.model.Pedido;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;
import static org.springframework.hateoas.TemplateVariable.VariableType.REQUEST_PARAM;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoFullOutputAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoFullOutput> {

    public PedidoFullOutputAssembler() {
        super(PedidoController.class, PedidoFullOutput.class);
    }

    @Override
    public PedidoFullOutput toModel(Pedido pedido) {
        var pedidoFullOutput = mapper(pedido, PedidoFullOutput.class);

        pedidoFullOutput.add(linkTo(methodOn(PedidoController.class)
                .buscar(pedidoFullOutput.getCodigo())).withSelfRel());

        var urlPedidos = linkTo(methodOn(PedidoController.class).listar(null, null)).toUri().toString();

        var variablesPageable = new TemplateVariables(
                new TemplateVariable("page", REQUEST_PARAM),
                new TemplateVariable("size", REQUEST_PARAM),
                new TemplateVariable("sort", REQUEST_PARAM)
        );

        var variablesFiltroPedido = new TemplateVariables(
                new TemplateVariable(PedidoFilter.Fields.clienteId, REQUEST_PARAM),
                new TemplateVariable(PedidoFilter.Fields.dataCriacaoFim, REQUEST_PARAM),
                new TemplateVariable(PedidoFilter.Fields.dataCriacaoInicio, REQUEST_PARAM),
                new TemplateVariable(PedidoFilter.Fields.restauranteId, REQUEST_PARAM)
        );

        pedidoFullOutput.add(Link.of(UriTemplate.of(urlPedidos, variablesPageable.concat(variablesFiltroPedido)), "pedidos"));

//        pedidoFullOutput.add().withRel("pedidos"));

        pedidoFullOutput.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
                .buscar(pedido.getRestaurante().getId())).withSelfRel());

        pedidoFullOutput.getFormaPagamento().add(linkTo(methodOn(FormaPagamentoController.class)
                .buscar(pedido.getFormaPagamento().getId())).withSelfRel());

        pedidoFullOutput.getCliente().add(linkTo(methodOn(UsuarioController.class)
                .buscar(pedido.getRestaurante().getId())).withSelfRel());

        pedidoFullOutput.getEnderecoEntrega().getCidade().add(linkTo(methodOn(CidadeController.class)
                .buscar(pedido.getEnderecoEntrega().getCidade().getId())).withSelfRel());

        pedidoFullOutput.getItens().forEach(itemPedidoOutput -> {
            itemPedidoOutput.add(linkTo(methodOn(RestauranteProdutoController.class)
                    .buscar(pedidoFullOutput.getRestaurante().getId(), itemPedidoOutput.getProduto().getId()))
                    .withRel("produto"));
        });

        return pedidoFullOutput;
    }

    @Override
    public CollectionModel<PedidoFullOutput> toCollectionModel(Iterable<? extends Pedido> estados) {
        return super.toCollectionModel(estados).add(linkTo(PedidoController.class).withSelfRel());
    }
}
