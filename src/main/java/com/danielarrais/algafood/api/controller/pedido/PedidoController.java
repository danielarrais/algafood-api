package com.danielarrais.algafood.api.controller.pedido;

import com.danielarrais.algafood.api.dto.input.pedido.PedidoInput;
import com.danielarrais.algafood.api.dto.input.pedido.filter.PedidoFilter;
import com.danielarrais.algafood.api.dto.output.pedido.PedidoFullOutput;
import com.danielarrais.algafood.api.dto.output.pedido.PedidoSimpleOutput;
import com.danielarrais.algafood.domain.model.Pedido;
import com.danielarrais.algafood.domain.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
        Pedido pedido = mapper(pedidoInput, Pedido.class);
        pedidoService.adicionar(pedido);
    }

    @GetMapping()
    public List<PedidoSimpleOutput> listar(PedidoFilter filtro) {
        var pedidos = pedidoService.listar(filtro);
        return mapper(pedidos, PedidoSimpleOutput.class);
    }

    @GetMapping("/{codigo}")
    public PedidoFullOutput buscar(@PathVariable String codigo) {
        var pedido = pedidoService.buscarObrigatorio(codigo);
        return mapper(pedido, PedidoFullOutput.class);
    }
}