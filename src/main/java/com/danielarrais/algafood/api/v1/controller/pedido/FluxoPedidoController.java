package com.danielarrais.algafood.api.v1.controller.pedido;

import com.danielarrais.algafood.core.util.MediaTypes;
import com.danielarrais.algafood.domain.service.FluxoPedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/pedidos", produces = MediaTypes.JSON_ALGAFOOD_V1)
public class FluxoPedidoController implements FluxoPedidoControllerOAS {
    private final FluxoPedidoService fluxoPedidoService;

    public FluxoPedidoController(FluxoPedidoService fluxoPedidoService) {
        this.fluxoPedidoService = fluxoPedidoService;
    }

    @PutMapping("/{codigo}/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancelar(@PathVariable String codigo) {
        fluxoPedidoService.cancelar(codigo);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{codigo}/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> confirmar(@PathVariable String codigo) {
        fluxoPedidoService.confirmar(codigo);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{codigo}/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> entregar(@PathVariable String codigo) {
        fluxoPedidoService.entregar(codigo);

        return ResponseEntity.noContent().build();
    }
}