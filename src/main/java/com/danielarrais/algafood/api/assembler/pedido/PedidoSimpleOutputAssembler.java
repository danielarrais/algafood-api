package com.danielarrais.algafood.api.assembler.pedido;

import com.danielarrais.algafood.api.controller.pedido.PedidoController;
import com.danielarrais.algafood.api.controller.restaurante.RestauranteController;
import com.danielarrais.algafood.api.controller.usuario.UsuarioController;
import com.danielarrais.algafood.api.dto.output.pedido.PedidoSimpleOutput;
import com.danielarrais.algafood.domain.filter.PedidoFilter;
import com.danielarrais.algafood.domain.model.Pedido;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

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
        var pedidoSimpleOutput = mapper(pedido, PedidoSimpleOutput.class);

        pedidoSimpleOutput.add(linkTo(methodOn(PedidoController.class)
                .buscar(pedidoSimpleOutput.getCodigo())).withSelfRel());

        pedidoSimpleOutput.add(linkTo(methodOn(PedidoController.class)
                .listar(new PedidoFilter(), Pageable.unpaged())).withRel("pedidos"));

        pedidoSimpleOutput.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
                .buscar(pedido.getRestaurante().getId())).withSelfRel());

        pedidoSimpleOutput.getCliente().add(linkTo(methodOn(UsuarioController.class)
                .buscar(pedido.getRestaurante().getId())).withSelfRel());

        return pedidoSimpleOutput;
    }

    @Override
    public CollectionModel<PedidoSimpleOutput> toCollectionModel(Iterable<? extends Pedido> estados) {
        return super.toCollectionModel(estados).add(linkTo(PedidoController.class).withSelfRel());
    }
}
