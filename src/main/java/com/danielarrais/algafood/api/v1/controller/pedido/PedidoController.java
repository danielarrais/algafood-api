package com.danielarrais.algafood.api.v1.controller.pedido;

import com.danielarrais.algafood.api.v1.assembler.pedido.PedidoFullOutputAssembler;
import com.danielarrais.algafood.api.v1.assembler.pedido.PedidoSimpleOutputAssembler;
import com.danielarrais.algafood.api.v1.dto.input.pedido.PedidoInput;
import com.danielarrais.algafood.api.v1.dto.output.pedido.PedidoFullOutput;
import com.danielarrais.algafood.api.v1.dto.output.pedido.PedidoSimpleOutput;
import com.danielarrais.algafood.core.data.PageableTranslator;
import com.danielarrais.algafood.domain.filter.PedidoFilter;
import com.danielarrais.algafood.domain.model.Pedido;
import com.danielarrais.algafood.domain.service.PedidoService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static com.danielarrais.algafood.core.data.PageWrapper.of;
import static com.danielarrais.algafood.util.ModelMapperUtils.mapper;

@RestController
@RequestMapping(path = "/pedidos", produces = "application/vnd.algafood.v1+json")
public class PedidoController implements PedidoControllerOAS {
    private final PedidoService pedidoService;
    private final PedidoFullOutputAssembler pedidoFullOutputAssembler;
    private final PedidoSimpleOutputAssembler pedidoSimpleOutputAssembler;
    private final PagedResourcesAssembler<Pedido> pagedResourcesAssembler;


    public PedidoController(PedidoService pedidoService,
                            PedidoFullOutputAssembler pedidoFullOutputAssembler,
                            PedidoSimpleOutputAssembler pedidoSimpleOutputAssembler,
                            PagedResourcesAssembler<Pedido> pagedResourcesAssembler) {
        this.pedidoService = pedidoService;
        this.pedidoFullOutputAssembler = pedidoFullOutputAssembler;
        this.pedidoSimpleOutputAssembler = pedidoSimpleOutputAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
        var pedido = mapper(pedidoInput, Pedido.class);
        pedidoService.adicionar(pedido);
    }

    @GetMapping()
    public PagedModel<PedidoSimpleOutput> listar(PedidoFilter filtro, Pageable pageable) {
        var pedidos = pedidoService.listar(filtro, traduzirPageable(pageable));

        return pagedResourcesAssembler.toModel(of(pedidos, pageable), pedidoSimpleOutputAssembler);
    }

    @GetMapping("/{codigo}")
    public PedidoFullOutput buscar(@PathVariable String codigo) {
        var pedido = pedidoService.buscarObrigatorio(codigo);
        return pedidoFullOutputAssembler.toModel(pedido);
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