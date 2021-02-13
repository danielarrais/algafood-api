package com.danielarrais.algafood.domain.listener;

import com.danielarrais.algafood.domain.event.PedidoConfirmadoEvent;
import com.danielarrais.algafood.domain.service.EnvioEmailService;
import com.danielarrais.algafood.domain.service.EnvioEmailService.Mensagem;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static com.danielarrais.algafood.domain.service.EnvioEmailService.TemplateEmail.PEDIDO_CONFIRMADO;

@Component
public class NotificacaoClientePedidoConfirmadoListener {
    private final EnvioEmailService envioEmailService;

    public NotificacaoClientePedidoConfirmadoListener(EnvioEmailService envioEmailService) {
        this.envioEmailService = envioEmailService;
    }

    @EventListener
    public void aoConfirmarPedido(final PedidoConfirmadoEvent event) {
        var pedido = event.getPedido();
        var mensagem = Mensagem.builder()
                .assunto(String.format("%s - Pedido confirmado!", pedido.getRestaurante().getNome()))
                .template(PEDIDO_CONFIRMADO)
                .outroDado("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail()).build();

        envioEmailService.enviar(mensagem);
    }
}
