package com.danielarrais.algafood.api.controller.pedido;

import com.danielarrais.algafood.domain.service.FluxoPedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class FluxoPedidoController {
    private final FluxoPedidoService fluxoPedidoService;

    public FluxoPedidoController(FluxoPedidoService fluxoPedidoService) {
        this.fluxoPedidoService = fluxoPedidoService;
    }

    @PutMapping("/{idPedido}/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable Long idPedido) {
        fluxoPedidoService.cancelar(idPedido);
    }

    @PutMapping("/{idPedido}/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable Long idPedido) {
        fluxoPedidoService.confirmar(idPedido);
    }


    @PutMapping("/{idPedido}/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregar(@PathVariable Long idPedido) {
        fluxoPedidoService.entregar(idPedido);
    }
}