package com.danielarrais.algafood.domain.model;

import com.danielarrais.algafood.core.validation.FreteGratis;
import com.danielarrais.algafood.core.validation.Groups;
import com.danielarrais.algafood.core.validation.Groups.OnlyId;
import com.danielarrais.algafood.core.validation.Multiplo;
import com.danielarrais.algafood.core.validation.TaxaFrete;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.danielarrais.algafood.domain.model.Restaurante.Fields.nome;
import static com.danielarrais.algafood.domain.model.Restaurante.Fields.taxaFrete;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FreteGratis(valorField = taxaFrete, descricaoField = nome, descricaoObrigatoria = "frete grátis")
public class Restaurante {
    @Id
    @NotNull(groups = Groups.OnlyId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @TaxaFrete
    @Multiplo(numero = 10)
    private BigDecimal taxaFrete;
    private Boolean ativo = true;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    @NotNull
    @Valid
    @ConvertGroup(to = OnlyId.class)
    @ManyToOne
    @JoinColumn(name = "cozinha_id")
    private Cozinha cozinha;

    @NotEmpty
    @ManyToMany
    @JoinTable(
            name = "forma_pagamento_restaurante",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<@Valid @ConvertGroup(to = OnlyId.class) FormaPagamento> formasPagamento;

    @Valid
    @Embedded
    private Endereco endereco;

    @JsonIgnore
    @OneToMany(mappedBy = Produto.Fields.restaurante)
    private List<@Valid Produto> produtos;
}
