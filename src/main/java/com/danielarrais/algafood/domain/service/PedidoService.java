package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.*;
import com.danielarrais.algafood.domain.repository.PedidoRepository;
import com.danielarrais.algafood.domain.service.validation.PedidoValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static com.danielarrais.algafood.domain.model.StatusPedido.CRIADO;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    private final PedidoValidation pedidoValidation;

    private final ProdutoService produtoService;
    private final RestauranteService restauranteService;
    private final FormaPagamentoService formaPagamentoService;
    private final UsuarioService usuarioService;
    private final CidadeService cidadeService;

    public PedidoService(PedidoRepository pedidoRepository,
                         PedidoValidation pedidoValidation,
                         ProdutoService produtoService,
                         RestauranteService restauranteService,
                         FormaPagamentoService formaPagamentoService,
                         UsuarioService usuarioService,
                         CidadeService cidadeService) {
        this.pedidoValidation = pedidoValidation;
        this.produtoService = produtoService;
        this.pedidoRepository = pedidoRepository;
        this.restauranteService = restauranteService;
        this.formaPagamentoService = formaPagamentoService;
        this.usuarioService = usuarioService;
        this.cidadeService = cidadeService;
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscar(long pedidoId) {
        return pedidoRepository.findById(pedidoId);
    }

    public Pedido buscarObrigatorio(Long pedidoId) {
        return buscar(pedidoId).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException("Pedido", pedidoId);
        });
    }

    @Transactional
    public void adicionar(Pedido pedido) {
        prencherDadosDoPedido(pedido);
        prencherDadosItens(pedido);

        pedido.calcularTotal();
        pedidoValidation.validate(pedido);

        pedidoRepository.save(pedido);
    }

    private void prencherDadosDoPedido(Pedido pedido) {
        Long clienteId = pedido.getCliente().getId();
        Long formaPagamentoId = pedido.getFormaPagamento().getId();
        Long restauranteId = pedido.getRestaurante().getId();
        Long cidadeId = pedido.getEnderecoEntrega().getCidade().getId();

        Restaurante restaurante = restauranteService.buscarObrigatorio(restauranteId);
        Usuario usuario = usuarioService.buscarObrigatorio(clienteId);
        FormaPagamento formaPagamento = formaPagamentoService.buscarObrigatorio(formaPagamentoId);
        Cidade cidade = cidadeService.buscarObrigatorio(cidadeId);

        pedido.setStatus(CRIADO);
        pedido.setRestaurante(restaurante);
        pedido.setCliente(usuario);
        pedido.setFormaPagamento(formaPagamento);
        pedido.setTaxaFrete(restaurante.getTaxaFrete());
        pedido.setDataCriacao(OffsetDateTime.now());
        pedido.getEnderecoEntrega().setCidade(cidade);
    }

    private void prencherDadosItens(Pedido pedido) {
        pedido.getItens().forEach(itemPedido -> {
            Long restauranteId = pedido.getRestaurante().getId();
            Long produtoId = itemPedido.getProduto().getId();
            Produto produto = produtoService.buscarObrigatorio(produtoId, restauranteId);

            itemPedido.setProduto(produto);
            itemPedido.setPrecoUnitario(produto.getPreco());
            itemPedido.setPedido(pedido);
        });
    }
}