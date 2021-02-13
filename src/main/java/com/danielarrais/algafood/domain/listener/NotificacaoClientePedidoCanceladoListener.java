package com.danielarrais.algafood.domain.listener;

import com.danielarrais.algafood.domain.event.PedidoCanceladoEvent;
import com.danielarrais.algafood.domain.service.EnvioEmailService;
import com.danielarrais.algafood.domain.service.EnvioEmailService.Mensagem;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import static com.danielarrais.algafood.domain.service.EnvioEmailService.TemplateEmail.PEDIDO_CANCELADO;
import static org.springframework.transaction.event.TransactionPhase.BEFORE_COMMIT;

@Component
public class NotificacaoClientePedidoCanceladoListener {
    private final EnvioEmailService envioEmailService;

    public NotificacaoClientePedidoCanceladoListener(EnvioEmailService envioEmailService) {
        this.envioEmailService = envioEmailService;
    }

    @EventListener
    @TransactionalEventListener(phase = BEFORE_COMMIT)
    public void aoConfirmarPedido(final PedidoCanceladoEvent event) {
        var pedido = event.getPedido();
        var mensagem = Mensagem.builder()
                .assunto(String.format("%s - Pedido cancelado!", pedido.getRestaurante().getNome()))
                .template(PEDIDO_CANCELADO)
                .outroDado("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail()).build();

        envioEmailService.enviar(mensagem);
    }
}
