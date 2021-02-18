package com.danielarrais.algafood.api.v1.assembler.pedido;

import com.danielarrais.algafood.api.v1.controller.pedido.PedidoController;
import com.danielarrais.algafood.api.v1.dto.output.pedido.PedidoFullOutput;
import com.danielarrais.algafood.domain.model.Pedido;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.api.v1.util.LinkBuilder.*;
import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@Component
public class PedidoFullOutputAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoFullOutput> {

    public PedidoFullOutputAssembler() {
        super(PedidoController.class, PedidoFullOutput.class);
    }

    @Override
    public PedidoFullOutput toModel(Pedido pedido) {
        var pedidoDTO = mapper(pedido, PedidoFullOutput.class);

        var cidade = pedidoDTO.getEnderecoEntrega().getCidade();
        var formaPagamento = pedidoDTO.getFormaPagamento();
        var cliente = pedidoDTO.getCliente();
        var restaurante = pedidoDTO.getRestaurante();
        var itens = pedidoDTO.getItens();

        pedidoDTO.add(linkBuscarPedido(pedido.getCodigo()));
        pedidoDTO.add(linkPedidos());

        restaurante.add(linkBuscarRestaurante(restaurante.getId()));
        formaPagamento.add(linkBuscarFormaPagamento(formaPagamento.getId()));
        cliente.add(linkBuscarUsuario(cliente.getId()));
        cidade.add(linkBuscarCidade(cidade.getId()));

        if (pedido.podeConfirmar()) {
            pedidoDTO.add(linkConfimacaoPedido(pedido.getCodigo()));
        }

        if (pedido.podeEntregar()) {
            pedidoDTO.add(linkEntregaPedido(pedido.getCodigo()));
        }

        if (pedido.podeCancelar()) {
            pedidoDTO.add(linkCancelamentoPedido(pedido.getCodigo()));
        }

        itens.forEach(itemPedido -> {
            itemPedido.add(linkBuscarProduto(restaurante.getId(), itemPedido.getProduto().getId()));
            itemPedido.add(linkFotoProduto(restaurante.getId(), itemPedido.getProduto().getId()));
        });

        return pedidoDTO;
    }

    @Override
    public CollectionModel<PedidoFullOutput> toCollectionModel(Iterable<? extends Pedido> estados) {
        return super.toCollectionModel(estados).add(linkPedidos().withSelfRel());
    }
}
