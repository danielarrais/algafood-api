package com.danielarrais.algafood.api.assembler.pedido;

import com.danielarrais.algafood.api.controller.pedido.PedidoController;
import com.danielarrais.algafood.api.controller.restaurante.RestauranteController;
import com.danielarrais.algafood.api.controller.usuario.UsuarioController;
import com.danielarrais.algafood.api.dto.output.pedido.PedidoSimpleOutput;
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
public class PedidoSimpleOutputAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoSimpleOutput> {

    public PedidoSimpleOutputAssembler() {
        super(PedidoController.class, PedidoSimpleOutput.class);
    }

    @Override
    public PedidoSimpleOutput toModel(Pedido pedido) {
        var pedidoSimpleOutput = mapper(pedido, PedidoSimpleOutput.class);

        pedidoSimpleOutput.add(linkTo(methodOn(PedidoController.class)
                .buscar(pedidoSimpleOutput.getCodigo())).withSelfRel());

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

        pedidoSimpleOutput.add(Link.of(UriTemplate.of(urlPedidos, variablesPageable.concat(variablesFiltroPedido)), "pedidos"));

        pedidoSimpleOutput.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
                .buscar(pedido.getRestaurante().getId())).withSelfRel());

        pedidoSimpleOutput.getCliente().add(linkTo(methodOn(UsuarioController.class)
                .buscar(pedido.getCliente().getId())).withSelfRel());

        return pedidoSimpleOutput;
    }

    @Override
    public CollectionModel<PedidoSimpleOutput> toCollectionModel(Iterable<? extends Pedido> estados) {
        return super.toCollectionModel(estados).add(linkTo(PedidoController.class).withSelfRel());
    }
}
