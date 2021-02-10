package com.danielarrais.algafood.api.controller.pedido;

import com.danielarrais.algafood.api.dto.input.pedido.PedidoInput;
import com.danielarrais.algafood.domain.filter.PedidoFilter;
import com.danielarrais.algafood.api.dto.output.pedido.PedidoFullOutput;
import com.danielarrais.algafood.api.dto.output.pedido.PedidoSimpleOutput;
import com.danielarrais.algafood.core.data.PageableTranslator;
import com.danielarrais.algafood.domain.model.Pedido;
import com.danielarrais.algafood.domain.service.PedidoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Map;

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
        var pedido = mapper(pedidoInput, Pedido.class);
        pedidoService.adicionar(pedido);
    }

    @GetMapping()
    public Page<PedidoSimpleOutput> listar(PedidoFilter filtro, Pageable pageable) {
        var pedidos = pedidoService.listar(filtro, traduzirPageable(pageable));
        return mapper(pedidos, PedidoSimpleOutput.class);
    }

    @GetMapping("/{codigo}")
    public PedidoFullOutput buscar(@PathVariable String codigo) {
        var pedido = pedidoService.buscarObrigatorio(codigo);
        return mapper(pedido, PedidoFullOutput.class);
    }

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamento = Map.of(
                "codigo", "codigo",
                "restaurante.nome", "restaurante.nome",
                "nomeCliente", "cliente.nome",
                "valorTotal", "valorTotal"
        );

        return PageableTranslator.translate(apiPageable, mapeamento);
    }
}