package com.danielarrais.algafood.domain.model;

import com.danielarrais.algafood.core.validation.Groups;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {
    @NotBlank
    @Column(name = "endereco_cep")
    private String cep;

    @NotBlank
    @Column(name = "endereco_logradouro")
    private String logradouro;

    @Column(name = "endereco_numero")
    private String numero;

    @Column(name = "endereco_complemento")
    private String complemento;

    @NotBlank
    @Column(name = "endereco_bairro")
    private String bairro;

    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name = "endereco_cidade_id")
    @ConvertGroup(to = Groups.OnlyId.class)
    private Cidade cidade;
}