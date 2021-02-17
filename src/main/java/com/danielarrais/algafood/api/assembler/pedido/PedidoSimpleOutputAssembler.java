package com.danielarrais.algafood.api.assembler.pedido;

import com.danielarrais.algafood.api.controller.pedido.PedidoController;
import com.danielarrais.algafood.api.dto.output.pedido.PedidoSimpleOutput;
import com.danielarrais.algafood.domain.model.Pedido;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.core.hateoas.LinkBuilder.*;
import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoSimpleOutputAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoSimpleOutput> {

    public PedidoSimpleOutputAssembler() {
        super(PedidoController.class, PedidoSimpleOutput.class);
    }

    @Override
    public PedidoSimpleOutput toModel(Pedido pedido) {
        var pedidoDTO = mapper(pedido, PedidoSimpleOutput.class);

        pedidoDTO.add(linkTo(methodOn(PedidoController.class)
                .buscar(pedidoDTO.getCodigo())).withSelfRel());

        var cliente = pedidoDTO.getCliente();
        var restaurante = pedidoDTO.getRestaurante();

        pedidoDTO.add(linkBuscarPedido(pedido.getCodigo()));
        pedidoDTO.add(linkPedidos());

        restaurante.add(linkBuscarRestaurante(restaurante.getId()));
        cliente.add(linkBuscarUsuario(cliente.getId()));

        if (pedido.podeConfirmar()) {
            pedidoDTO.add(linkConfimacaoPedido(pedido.getCodigo()));
        }

        if (pedido.podeEntregar()) {
            pedidoDTO.add(linkEntregaPedido(pedido.getCodigo()));
        }

        if (pedido.podeCancelar()) {
            pedidoDTO.add(linkCancelamentoPedido(pedido.getCodigo()));
        }

        return pedidoDTO;
    }

    @Override
    public CollectionModel<PedidoSimpleOutput> toCollectionModel(Iterable<? extends Pedido> estados) {
        return super.toCollectionModel(estados).add(linkTo(PedidoController.class).withSelfRel());
    }
}
