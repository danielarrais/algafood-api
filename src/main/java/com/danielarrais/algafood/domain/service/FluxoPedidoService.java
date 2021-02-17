package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.NegocioException;
import com.danielarrais.algafood.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.danielarrais.algafood.domain.model.StatusPedido.CONFIRMADO;
import static com.danielarrais.algafood.domain.model.StatusPedido.CRIADO;

@Service
public class FluxoPedidoService {

    private final PedidoService pedidoService;
    private final PedidoRepository pedidoRepository;

    public FluxoPedidoService(PedidoService pedidoService, PedidoRepository pedidoRepository) {
        this.pedidoService = pedidoService;
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public void cancelar(String codigo) {
        var pedido = pedidoService.buscarObrigatorio(codigo);

        if (pedido.getStatus().podeAlterarPara(CRIADO)) {
            pedido.cancelar();
            pedidoRepository.save(pedido);
        } else {
            throw new NegocioException("Somente pedido com status %s podem ser cancelados", CRIADO.name());
        }
    }

    @Transactional
    public void entregar(String codigo) {
        var pedido = pedidoService.buscarObrigatorio(codigo);

        if (pedido.getStatus().podeAlterarPara(CONFIRMADO)) {
            pedido.entregar();
            pedidoRepository.save(pedido);
        } else {
            throw new NegocioException("Somente pedido com status %s podem ser entregues", CONFIRMADO.name());
        }
    }

    @Transactional
    public void confirmar(String codigo) {
        var pedido = pedidoService.buscarObrigatorio(codigo);

        if (pedido.getStatus().podeAlterarPara(CRIADO)) {
            pedido.confirmar();
            pedidoRepository.save(pedido);
        } else {
            throw new NegocioException("Somente pedido com status %s podem ser entregues", CRIADO.name());
        }
    }
}