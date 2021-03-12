package com.danielarrais.algafood.domain.model;

import com.danielarrais.algafood.core.validation.Groups.OnlyId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Boolean ativo = Boolean.TRUE;
    private Boolean aberto = Boolean.TRUE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id")
    private Cozinha cozinha;

    @ManyToMany
    @JoinTable(
            name = "forma_pagamento_restaurante",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private Set<@ConvertGroup(to = OnlyId.class) FormaPagamento> formasPagamento = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "usuario_responsavel_restaurante",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<@ConvertGroup(to = OnlyId.class) Usuario> responsaveis = new HashSet<>();

    @Embedded
    private Endereco endereco;

    @JsonIgnore
    @OneToMany(mappedBy = Produto.Fields.restaurante)
    private List<Produto> produtos;

    @CreationTimestamp
    @Column(updatable = false)
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp
    private OffsetDateTime dataAtualizacao;

    public void ativar() {
        setAtivo(true);
    }

    public void inativar() {
        setAtivo(false);
    }

    public void abrir() {
        setAberto(true);
    }

    public void fechar() {
        setAberto(false);
    }

    public void adicionarFormaPagamento(FormaPagamento formaPagamento) {
        getFormasPagamento().add(formaPagamento);
    }

    public void removerFormaPagamento(FormaPagamento formaPagamento) {
        getFormasPagamento().remove(formaPagamento);
    }

    public void adicionarResponsavel(Usuario usuario) {
        getResponsaveis().add(usuario);
    }

    public void removerResponsavel(Usuario usuario) {
        getResponsaveis().remove(usuario);
    }

    public boolean isAceitaFormaDePagamento(FormaPagamento formaPagamento) {
        return !CollectionUtils.isEmpty(getFormasPagamento()) && getFormasPagamento().contains(formaPagamento);
    }
}
