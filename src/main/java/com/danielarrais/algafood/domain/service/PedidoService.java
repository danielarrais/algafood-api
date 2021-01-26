package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.Pedido;
import com.danielarrais.algafood.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscar(long pedidoId) {
        return pedidoRepository.findById(pedidoId);
    }

    public Pedido buscarObrigatorio(long pedidoId) {
        return buscar(pedidoId).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException("Pedido", pedidoId);
        });
    }
}