package com.danielarrais.algafood.domain.model;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private Boolean ativo;

    @CreationTimestamp
    private LocalDate dataCadastro;

    @UpdateTimestamp
    private LocalDate dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "cozinha_id")
    private Cozinha cozinha;

    @ManyToMany
    @JoinTable(
            name = "forma_pagamento_restaurante",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento;
}
