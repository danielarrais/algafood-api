package com.danielarrais.algafood.domain.model;

import com.danielarrais.algafood.domain.event.PedidoCanceladoEvent;
import com.danielarrais.algafood.domain.event.PedidoConfirmadoEvent;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.danielarrais.algafood.domain.model.ItemPedido.Fields.pedido;
import static com.danielarrais.algafood.domain.model.StatusPedido.CANCELADO;
import static com.danielarrais.algafood.domain.model.StatusPedido.ENTREGUE;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Pedido extends AbstractAggregateRoot<Pedido> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(updatable = false, nullable = false)
    private String codigo = UUID.randomUUID().toString();

    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataEntrega;
    private OffsetDateTime dataCancelamento;
    private Endereco enderecoEntrega;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forma_pagamento_id")
    private FormaPagamento formaPagamento;

    @OneToMany(mappedBy = pedido, cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false)
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp
    private OffsetDateTime dataAtualizacao;

    public void confirmar() {
        setStatus(ENTREGUE);
        setDataConfirmacao(OffsetDateTime.now());

        registerEvent(new PedidoConfirmadoEvent(this));
    }

    public void cancelar() {
        setStatus(CANCELADO);
        setDataEntrega(OffsetDateTime.now());

        registerEvent(new PedidoCanceladoEvent(this));
    }

    public void entregar() {
        setStatus(ENTREGUE);
        setDataCancelamento(OffsetDateTime.now());
    }

    public void calcularTotal() {
        calcularSubtotal();

        this.valorTotal = subtotal.add(taxaFrete);
    }

    private void calcularSubtotal() {
        getItens().forEach(ItemPedido::calcularTotal);

        this.subtotal = itens.stream()
                .map(ItemPedido::getPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}