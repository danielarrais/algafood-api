package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.NegocioException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

import static com.danielarrais.algafood.domain.model.StatusPedido.*;

@Service
public class FluxoPedidoService {

    private final PedidoService pedidoService;

    public FluxoPedidoService(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Transactional
    public void cancelar(String codigo) {
        var pedido = pedidoService.buscarObrigatorio(codigo);

        if (pedido.getStatus().equals(CRIADO)) {
            pedido.setStatus(CANCELADO);
            pedido.setDataCancelamento(OffsetDateTime.now());
        } else {
            throw new NegocioException("Somente pedido com status %s podem ser cancelados", CRIADO.name());
        }
    }

    @Transactional
    public void entregar(String codigo) {
        var pedido = pedidoService.buscarObrigatorio(codigo);

        if (pedido.getStatus().equals(CONFIRMADO)) {
            pedido.setStatus(CANCELADO);
            pedido.setDataEntrega(OffsetDateTime.now());
        } else {
            throw new NegocioException("Somente pedido com status %s podem ser entregues", CONFIRMADO.name());
        }
    }

    @Transactional
    public void confirmar(String codigo) {
        var pedido = pedidoService.buscarObrigatorio(codigo);

        if (pedido.getStatus().equals(CRIADO)) {
            pedido.setStatus(ENTREGUE);
            pedido.setDataConfirmacao(OffsetDateTime.now());
        } else {
            throw new NegocioException("Somente pedido com status %s podem ser entregues", CRIADO.name());
        }
    }
}