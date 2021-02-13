package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.NegocioException;
import com.danielarrais.algafood.domain.service.EnvioEmailService.Mensagem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

import static com.danielarrais.algafood.domain.model.StatusPedido.*;
import static com.danielarrais.algafood.domain.service.EnvioEmailService.TemplateEmail.*;

@Service
public class FluxoPedidoService {

    private final PedidoService pedidoService;
    private final EnvioEmailService envioEmailService;

    public FluxoPedidoService(PedidoService pedidoService, EnvioEmailService envioEmailService) {
        this.pedidoService = pedidoService;
        this.envioEmailService = envioEmailService;
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

        var mensagem = Mensagem.builder()
                .assunto(String.format("%s - Pedido confirmado!", pedido.getRestaurante().getNome()))
                .template(PEDIDO_CONFIRMADO)
                .outroDado("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail()).build();

        if (pedido.getStatus().equals(CRIADO)) {
            pedido.setStatus(ENTREGUE);
            pedido.setDataConfirmacao(OffsetDateTime.now());

            envioEmailService.enviar(mensagem);
        } else {
            throw new NegocioException("Somente pedido com status %s podem ser entregues", CRIADO.name());
        }
    }
}