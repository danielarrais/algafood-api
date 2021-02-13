package com.danielarrais.algafood.domain.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.danielarrais.algafood.domain.model.ItemPedido.Fields.pedido;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {
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

    private void calcularSubtotal() {
        getItens().forEach(ItemPedido::calcularTotal);

        this.subtotal = itens.stream()
                .map(ItemPedido::getPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void calcularTotal() {
        calcularSubtotal();

        this.valorTotal = subtotal.add(taxaFrete);
    }
}