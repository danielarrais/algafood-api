package com.danielarrais.algafood.api.controller.pedido;

import com.danielarrais.algafood.api.dto.output.pedido.PedidoFullOutput;
import com.danielarrais.algafood.api.dto.output.pedido.PedidoSimpleOutput;
import com.danielarrais.algafood.domain.service.PedidoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping()
    public List<PedidoSimpleOutput> listar() {
        var pedidos = pedidoService.listar();
        return mapper(pedidos, PedidoSimpleOutput.class);
    }

    @GetMapping("/{id}")
    public PedidoFullOutput buscar(@PathVariable Long id) {
        var pedido = pedidoService.buscarObrigatorio(id);
        return mapper(pedido, PedidoFullOutput.class);
    }
}