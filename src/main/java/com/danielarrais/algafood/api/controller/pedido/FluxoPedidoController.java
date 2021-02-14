package com.danielarrais.algafood.api.controller.pedido;

import com.danielarrais.algafood.domain.service.FluxoPedidoService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Alteração de Status de Pedido")
@RestController
@RequestMapping("/pedidos")
public class FluxoPedidoController {
    private final FluxoPedidoService fluxoPedidoService;

    public FluxoPedidoController(FluxoPedidoService fluxoPedidoService) {
        this.fluxoPedidoService = fluxoPedidoService;
    }

    @PutMapping("/{codigo}/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable String codigo) {
        fluxoPedidoService.cancelar(codigo);
    }

    @PutMapping("/{codigo}/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable String codigo) {
        fluxoPedidoService.confirmar(codigo);
    }


    @PutMapping("/{codigo}/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregar(@PathVariable String codigo) {
        fluxoPedidoService.entregar(codigo);
    }
}